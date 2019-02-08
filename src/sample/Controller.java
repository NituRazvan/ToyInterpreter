package sample;

import Exception.ControllerException;
import Model.FileHandling.FileData;
import Model.ProgramState;
import Repository.IRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private IRepo repo;
    private ExecutorService executor;
    public Controller(IRepo repo) {
        this.repo = repo;
        this.executor = Executors.newFixedThreadPool(2);
    }
    public int noPrgStates(){
        return repo.getPrgList().size();
    }

    public ProgramState getPrgStateByIndex(int index){
        return  repo.getPrgList().get(index);
    }

    public ObservableList<String> getPrgStatesID(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(ProgramState i : repo.getPrgList())
            list.add( String.valueOf(i.getID()));

        return list;
    }
    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());

    }
    Map<Integer,Integer> conservativeGarbageCollector(List<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e-> symTableValues.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    public ProgramState closeAllOpenFiles(ProgramState prg){
        prg.clearFileFromSym();
        Collection<FileData> myList = prg.getFileTable().getAllElems();
        myList.stream().forEach(el->{try{el.getReader().close();}catch (IOException e) {System.out.println(e.getMessage());}});
        prg.clearFileTable();
        return null;

    }
    void oneStepForAllPrg(List<ProgramState> prgList)
    {
        prgList.forEach(prg ->repo.logPrgStateExec(prg));
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());

        List<ProgramState> newList = null;
        try {
            newList = executor.invokeAll(callList)
                    .stream()
                    .map(  future ->
                    {
                        try {
                            return future.get();
                        } catch (InterruptedException e) {
                            throw new ControllerException(e.getMessage());
                        } catch (ExecutionException e) {
                            throw new ControllerException(e.getMessage());
                        }
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new ControllerException(e.getMessage());
        }

        prgList.addAll(newList);
        prgList.forEach(prg ->repo.logPrgStateExec(prg));
        repo.setPrgList(prgList);
    }
    public boolean allStepGUI() {

        List<ProgramState> prgList = removeCompletedPrg(repo.getPrgList());

        if(prgList.size() > 0){
            prgList.forEach(p->p.getHeap().setContent(new HashMap<Integer, Integer>(conservativeGarbageCollector(p.getSymTable().getAllValues(),
                    p.getHeap().getHeap()))));
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
            return true;
        }
        else {
            executor.shutdownNow();
            List<ProgramState> tmp = repo.getPrgList();
            tmp.forEach(p -> closeAllOpenFiles(p));
            repo.setPrgList(prgList);
            return false;
        }
    }


    @Override
    public String toString(){
        return "Controller: " + repo.toString();
    }
}
