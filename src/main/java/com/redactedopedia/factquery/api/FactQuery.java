package com.redactedopedia.factquery.api;

import com.redactedopedia.factquery.api.expression.Expression;

public class FactQuery {
    private final Expression expression;
    private final String security;

    public FactQuery(Expression expression, String security) {
        this.expression = expression;
        this.security = security;
    }

    public Expression getExpression() {
        return expression;
    }

    public String getSecurity() {
        return security;
    }
}
