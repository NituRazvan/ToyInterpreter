package Model.HeapStatements;

import Model.Expression.ExpressionInterface;
import Model.HeapStatements.GenIDHeap;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class HeapAllocation implements StmtInterface {

    String varName;
    ExpressionInterface exp;

    public HeapAllocation(String varName, ExpressionInterface exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        DictionaryInterface<String, Integer> dict = state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();

        int id = GenIDHeap.getID();
        int val = exp.evaluate(dict, heap);

        heap.add(id, val);
        if(dict.exists(varName))
            dict.setValue(varName, id);
        else
            dict.setValue(varName, id);

        return null;
    }

    @Override
    public String toString(){
        return "NewH(" + varName + ", " + exp + ")";
    }
}