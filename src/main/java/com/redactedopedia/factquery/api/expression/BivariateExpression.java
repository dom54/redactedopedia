package com.redactedopedia.factquery.api.expression;

import com.redactedopedia.factquery.api.expression.operator.Operator;

public class BivariateExpression implements Expression {

    private Operator fn;
    private Expression a;
    private Expression b;

    public BivariateExpression(Operator fn, Expression a, Expression b) {
        this.fn = fn;
        this.a = a;
        this.b = b;
    }

    public Operator getFn() {
        return fn;
    }

    public Expression getA() {
        return a;
    }

    public Expression getB() {
        return b;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
