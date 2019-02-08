package Model.FileHandling;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Exception.FileException;
import Model.Utils.HeapInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class CloseRFile implements StmtInterface {

    private ExpressionInterface exp;

    public CloseRFile(ExpressionInterface exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state){

        DictionaryInterface<String, Integer> dic = state.getSymTable();
        FileTableInterface<Integer, FileData> fileTable = state.getFileTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        int id = exp.evaluate(dic, heap);

        if(!fileTable.contains(id)){
            throw new FileException("The file doesn't exist!");
        }

        FileData fileData = fileTable.get(id);
        BufferedReader reader = fileData.getReader();

        try{
            reader.close();
        }catch (IOException e){
            throw new FileException("Can't close the file!");
        }

        fileTable.delete(id);

        return null;
    }

    @Override
    public String toString(){
        return "close(" + exp + ")";
    }
}
