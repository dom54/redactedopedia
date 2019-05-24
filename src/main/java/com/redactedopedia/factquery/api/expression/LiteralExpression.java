package com.redactedopedia.factquery.api.expression;

import java.math.BigDecimal;

public class LiteralExpression implements Expression {
    private final BigDecimal number;

    public LiteralExpression(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
