package Model.HeapStatements;


import Model.Expression.ExpressionInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Exception.VariableNotDefined;

public class HeapReading implements ExpressionInterface {

    String varName;

    public HeapReading(String varName) {
        this.varName = varName;
    }


    @Override
    public int evaluate(DictionaryInterface<String, Integer> dict, HeapInterface<Integer, Integer> heap) {

        if(!dict.exists(varName)) {

            throw new VariableNotDefined("This key is not in our symTable");
        }
        int heapID = dict.getValue(varName);

        if(!heap.contains(heapID)) {

            throw new VariableNotDefined("This key is not in our heapTable");
        }

        return  heap.get(heapID);
    }

    @Override
    public String toString(){
        return "heapReading(" + varName +")";
    }

}
