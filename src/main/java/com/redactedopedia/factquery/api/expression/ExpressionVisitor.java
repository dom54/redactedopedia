package com.redactedopedia.factquery.api.expression;

public interface ExpressionVisitor {
    void visit(PropertyExpression propertyExpression);
    void visit(LiteralExpression literalExpression);
    void visit(BivariateExpression bivariateExpression);
}
