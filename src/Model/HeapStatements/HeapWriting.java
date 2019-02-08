package Model.HeapStatements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Exception.VariableNotDefined;

public class HeapWriting implements StmtInterface {

    String varName;
    ExpressionInterface exp;

    public HeapWriting(String varName, ExpressionInterface exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        DictionaryInterface<String, Integer> dict = state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();

        if(!dict.exists(varName))
            throw new VariableNotDefined("This key is not in our symTable");
        int heapID = dict.getValue(varName);

        if(!heap.contains(heapID))
            throw new VariableNotDefined("This key is not in our heapTable");

        int val = exp.evaluate(dict, heap);
        heap.update(heapID, val);

        return null;
    }

    @Override
    public String toString(){
        return "heapWriting("+varName+","+exp+")";
    }
}
