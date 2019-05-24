package com.redactedopedia.factquery.data;

import com.redactedopedia.factquery.app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CsvSecurityDataRepositoryTest {

    @Autowired
    private CsvSecurityDataRepository repository;

    @Test
    public void should_correctly_read_single_fact() {
        BigDecimal factForSecurity = repository.getFactForSecurity("ABC", "shares");
        assertThat(factForSecurity.doubleValue(), closeTo(10d,0.0001));

    }

    @Test
    public void should_correctly_read_all_securities() {
        assertThat(repository.countSecurities(), equalTo(10L));
    }

    @Test
    public void should_correctly_read_single_security() {
        Security abc = repository.getSecurity("ABC");
        assertThat(abc.getFact("shares").doubleValue(), closeTo(10d,0.0001));
        assertThat(abc.getFact("assets").doubleValue(), closeTo(7d,0.0001));
        assertThat(abc.getFact("price").doubleValue(), closeTo(1d,0.0001));
        assertThat(abc.getFact("dps").doubleValue(), closeTo(3,0.0001));
        assertThat(abc.getFact("free_cash_flow").doubleValue(), closeTo(6,0.0001));
        assertThat(abc.getFact("liabilities").doubleValue(), closeTo(8,0.0001));
        assertThat(abc.getFact("eps").doubleValue(), closeTo(2,0.0001));
        assertThat(abc.getFact("debt").doubleValue(), closeTo(9,0.0001));
        assertThat(abc.getFact("sales").doubleValue(), closeTo(4,0.0001));
        assertThat(abc.getFact("shares").doubleValue(), closeTo(10,0.0001));
    }


}