package com.redactedopedia.factquery.api;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public class FactQueryResult {
    private final BigDecimal result;

    @JsonCreator
    public FactQueryResult(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }
}
