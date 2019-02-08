package Model;

import Model.FileHandling.FileData;
import Exception.EmptyStack;
import Model.FileHandling.FileTable;
import Model.FileHandling.FileTableInterface;
import Model.Statements.StmtInterface;
import Model.Utils.*;

import java.util.Map;
import java.util.stream.Collectors;

public class ProgramState {
    private StackInterface<StmtInterface> stack;
    private DictionaryInterface<String, Integer> SymTable;
    private ListInterface<Integer> list;
    private FileTableInterface<Integer, FileData> fileTable;
    private HeapInterface<Integer, Integer> heap;
    private Integer ID;

    public ProgramState(StackInterface<StmtInterface> stack, DictionaryInterface<String, Integer> symTable,
                        ListInterface<Integer> list, FileTableInterface<Integer, FileData> fileTable,
                        HeapInterface<Integer, Integer> heap, Integer ID) {
        this.stack = stack;
        this.SymTable = symTable;
        this.list = list;
        this.fileTable = fileTable;
        this.heap = heap;
        this.ID = ID;
    }
    public Integer getID() {
        return ID;
    }

    public boolean isNotCompleted(){
        return !stack.isEmpty();
    }

    public ProgramState oneStep(){
        if(stack.isEmpty())
            throw new EmptyStack("Empty Stack");
        StmtInterface crtStmt = stack.pop();
        return crtStmt.execute(this);
    }
    public StackInterface<StmtInterface> getStack() {
        return stack;
    }

    public void setStack(StackInterface<StmtInterface> stack) {
        this.stack = stack;
    }

    public DictionaryInterface<String, Integer> getSymTable() {
        return SymTable;
    }

    public void setSymTable(DictionaryInterface<String, Integer> symTable) {
        SymTable = symTable;
    }

    public ListInterface<Integer> getList() {
        return list;
    }

    public void setList(ListInterface<Integer> list) {
        this.list = list;
    }

    public FileTableInterface<Integer, FileData> getFileTable() { return fileTable; }

    public void setFileTable(FileTableInterface<Integer, FileData> fileTable) { this.fileTable = fileTable; }

    public HeapInterface<Integer, Integer> getHeap() {
        return heap;
    }

    public void setHeap(HeapInterface<Integer, Integer> heap) {
        this.heap = heap;
    }

    public void clearFileTable(){this.fileTable.clear();}

    public void clearFileFromSym(){this.SymTable.setMap(SymTable.getMap().entrySet().stream().filter(
            e-> fileTable.getMap().containsKey(e)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));}

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append("\nExeStack  "+ this.ID +" \n");
        for(StmtInterface s : stack.getElements()) {
            bld.append(s).append("\n");
        }

        bld.append("\nSymTable\n");
        for(String key : SymTable.getElements()){
            bld.append(key).append(" : ").append(SymTable.getValue(key)).append("\n");
        }

        bld.append("\nOut:\n");
        for(Integer i : list.getElements()){
            bld.append(i).append("\n");
        }

        bld.append("\nFileTable:\n");
        for(Integer i : fileTable.getElements()) {
            bld.append(i).append(fileTable.get(i).getFileName()).append("\n");
        }
        bld.append("\nHeap:\n");
        for(Integer key: heap.getElements())
            bld.append(key + "-> " +heap.get(key) + "\n");

        return bld.toString();
    }
}
