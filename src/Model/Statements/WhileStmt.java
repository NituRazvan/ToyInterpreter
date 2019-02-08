package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Model.Utils.StackInterface;

public class WhileStmt implements StmtInterface {

    ExpressionInterface exp;
    StmtInterface statement;

    public WhileStmt(ExpressionInterface exp, StmtInterface statement) {
        this.exp = exp;
        this.statement = statement;
    }


    @Override
    public ProgramState execute(ProgramState state) {

        StackInterface<StmtInterface> stack = state.getStack();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        DictionaryInterface<String, Integer> dict = state.getSymTable();

        int val = exp.evaluate(dict, heap);

        if(val != 0) {
            stack.add(this);
            stack.add(statement);
        }

        return null;
    }

    @Override
    public String toString(){
        return "While("+exp+"){"+statement+"}";
    }
}