package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.Utils.*;
import Model.ProgramState;

public class IfStmt implements StmtInterface{
    private ExpressionInterface expr;
    private StmtInterface thenStmt;
    private StmtInterface elseStmt;

    public IfStmt(ExpressionInterface expression, StmtInterface thenS, StmtInterface elseS){
        expr = expression;
        thenStmt = thenS;
        elseStmt = elseS;
    }

    public ProgramState execute(ProgramState state){
        DictionaryInterface<String, Integer> dic = state.getSymTable();
        StackInterface<StmtInterface> stack = state.getStack();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        int val = expr.evaluate(dic, heap);

        if(val != 0)
            stack.add(thenStmt);
        else
            stack.add(elseStmt);
        return null;
    }

    @Override
    public String toString(){
        return "if(" + expr + ") then(" + thenStmt +  ")else(" + elseStmt + ") ;";
    }
}
