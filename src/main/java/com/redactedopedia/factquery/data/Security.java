package com.redactedopedia.factquery.data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Security {

    private final Integer id;
    private final String symbol;
    private final Map<String, BigDecimal> facts = new HashMap<String,BigDecimal>();

    public Security(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public BigDecimal getFact(String factName) {
        return facts.get(factName);
    }
    public void addFact(String factName, BigDecimal value) {
        facts.put(factName,value);
    }
}
