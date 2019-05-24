package com.redactedopedia.factquery.api.expression.operator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public enum Operator {
    PLUS("+", (a,b) -> (a.add(b))),
    MULT("*", (a,b) -> (a.multiply(b))),
    DIV("/", (a,b) -> (a.divide(b, 10,RoundingMode.HALF_UP))),
    MINUS("-", (a,b) -> (a.subtract(b)));

    private final String code;
    private OperatorFunc fn;

    Operator(String code, OperatorFunc fn) {
        this.code = code;
        this.fn = fn;
    }

    public String getCode() {
        return code;
    }

    public static Operator fromCode(String code) {
       return  Arrays.stream(Operator.values())
               .filter(o -> code.equals(o.getCode()))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Invalid code"));
    }

    public BigDecimal apply(BigDecimal operandA, BigDecimal operandB) {
        return fn.apply(operandA, operandB);
    }
}
