package com.redactedopedia.factquery.api.data;

import java.math.BigDecimal;

public interface SecurityDataRepository {
    BigDecimal getFactForSecurity(String security, String property);
}
