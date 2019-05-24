package com.redactedopedia.factquery.service.helper;

import com.redactedopedia.factquery.api.FactQuery;
import com.redactedopedia.factquery.api.FactQueryResult;
import com.redactedopedia.factquery.api.data.SecurityDataRepository;
import com.redactedopedia.factquery.api.expression.*;

import java.math.BigDecimal;

public class ExpressionEvaluator implements ExpressionVisitor {

    private SecurityDataRepository repository;
    private String symbol;
    private BigDecimal lastResult;

    public ExpressionEvaluator(SecurityDataRepository repository, String symbol) {
        this.repository = repository;
        this.symbol = symbol;
    }

    public FactQueryResult getResult(FactQuery query) {
        query.getExpression().accept(this);
        return new FactQueryResult(lastResult);
    }

    @Override
    public void visit(PropertyExpression propertyExpression) {
        lastResult = repository.getFactForSecurity(symbol, propertyExpression.getProperty());
    }

    @Override
    public void visit(LiteralExpression literalExpression) {
        lastResult = literalExpression.getNumber();
    }

    @Override
    public void visit(BivariateExpression bivariateExpression) {
        lastResult = bivariateExpression.getFn().apply(
                acceptAndReturn(bivariateExpression.getA()),
                acceptAndReturn(bivariateExpression.getB())
        );
    }

    private BigDecimal acceptAndReturn(Expression expression) {
        expression.accept(this);
        return lastResult;
    }
}
