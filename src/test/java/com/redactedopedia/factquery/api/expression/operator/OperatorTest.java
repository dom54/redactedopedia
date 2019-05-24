package com.redactedopedia.factquery.api.expression.operator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class OperatorTest {

    @Test
    public void testAdd() {
        assertThat(Operator.PLUS.apply(BigDecimal.ONE, BigDecimal.TEN), equalTo(BigDecimal.valueOf(11)));
    }

    @Test
    public void testSubtract() {
        assertThat(Operator.MINUS.apply(BigDecimal.ONE, BigDecimal.TEN), equalTo(BigDecimal.valueOf(-9)));
    }

    @Test
    public void testMult() {
        assertThat(Operator.MULT.apply(BigDecimal.ONE, BigDecimal.TEN), equalTo(BigDecimal.valueOf(10)));
    }

    @Test
    public void testDiv() {
        assertThat(Operator.DIV.apply(BigDecimal.ONE, BigDecimal.TEN).setScale(1), equalTo(BigDecimal.valueOf(0.1)));
    }

}