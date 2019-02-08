package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.Utils.*;
import Model.ProgramState;

public class PrintStmt implements StmtInterface{

    private ExpressionInterface expr;

    public PrintStmt(ExpressionInterface expression){
        expr = expression;
    }

    @Override
    public ProgramState execute(ProgramState state){
        ListInterface<Integer> list = state.getList();
        DictionaryInterface<String, Integer> dic = state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        int val = expr.evaluate(dic, heap);
        list.add(val);
        return null;
    }

    @Override
    public String toString(){
        return "print(" + expr + ");";
    }
}
