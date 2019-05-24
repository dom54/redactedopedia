package com.redactedopedia.factquery.api.expression.operator;

import java.math.BigDecimal;

public interface OperatorFunc {
    BigDecimal apply(BigDecimal a, BigDecimal b);
}
