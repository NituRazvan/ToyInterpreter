package Model.Statements;

import Model.FileHandling.FileData;
import Model.FileHandling.FileTableInterface;
import Model.ProgramState;
import Model.Utils.*;

public class ForkStmt implements StmtInterface {

    StmtInterface state;

    public ForkStmt(StmtInterface state){
        this.state = state;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        StackInterface<StmtInterface> exeStack = new Stack<>();
        ListInterface<Integer> out = state.getList();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        FileTableInterface<Integer, FileData> fileTable = state.getFileTable();
        DictionaryInterface<String, Integer> symTable = state.getSymTable();
        exeStack.add(this.state);
        Integer ID = GenIDFork.getID();
        return new ProgramState(exeStack, symTable, out, fileTable, heap, ID);
    }
    @Override
    public String toString(){
        return "fork("+state+")";
    }
}
