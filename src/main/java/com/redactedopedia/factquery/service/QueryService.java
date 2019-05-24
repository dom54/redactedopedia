package com.redactedopedia.factquery.service;

import com.redactedopedia.factquery.api.FactQueryResult;
import com.redactedopedia.factquery.api.data.SecurityDataRepository;
import com.redactedopedia.factquery.service.helper.ExpressionEvaluator;
import com.redactedopedia.factquery.api.FactQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    @Autowired
    private SecurityDataRepository repository;

    public FactQueryResult execute(FactQuery query) {
        ExpressionEvaluator evaluator = buildExpressionEvaluator(query.getSecurity());
        return evaluator.getResult(query);
    }

    private ExpressionEvaluator buildExpressionEvaluator(String symbol) {
        return new ExpressionEvaluator(repository, symbol);
    }
}
