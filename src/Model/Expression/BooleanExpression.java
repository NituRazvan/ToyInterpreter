package Model.Expression;

import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Exception.InvalidOperator;

public class BooleanExpression implements ExpressionInterface {
    ExpressionInterface left, right;
    String operator;

    public BooleanExpression(String op, ExpressionInterface l, ExpressionInterface r){
        operator = op;
        left = l;
        right = r;
    }
    @Override
    public int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap) {
        int left = this.left.evaluate(dic, heap);
        int right = this.right.evaluate(dic, heap);

        switch (operator){
            case "<":
                if (left < right)
                    return 1;
                else
                    return 0;
            case "<=":
                if (left <= right)
                    return 1;
                else
                    return 0;
            case ">":
                if (left > right)
                    return 1;
                else
                    return 0;
            case ">=":
                if (left >= right)
                    return 1;
                else
                    return 0;
            case "==":
                if (left == right)
                    return 1;
                else
                    return 0;
            case "!=":
                if (left != right)
                    return 1;
                else
                    return 0;
        }
        throw new InvalidOperator("Invalid operator!");
    }

    @Override
    public String toString(){
        return "" + left + operator + right;
    }
}
