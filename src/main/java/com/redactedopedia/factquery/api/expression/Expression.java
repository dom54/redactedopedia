package com.redactedopedia.factquery.api.expression;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.redactedopedia.factquery.jackson.ExpressionDeserializer;

@JsonDeserialize(using = ExpressionDeserializer.class)
public interface Expression {
    void accept(ExpressionVisitor visitor);
}
