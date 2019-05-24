package com.redactedopedia.factquery.api.expression;

public class PropertyExpression implements Expression {

    private final String property;

    public PropertyExpression(String textValue) {
        this.property = textValue;
    }

    public String getProperty() {
        return property;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
