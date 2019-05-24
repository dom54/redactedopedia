package com.redactedopedia.factquery.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.redactedopedia.factquery.api.expression.BivariateExpression;
import com.redactedopedia.factquery.api.expression.Expression;
import com.redactedopedia.factquery.api.expression.LiteralExpression;
import com.redactedopedia.factquery.api.expression.PropertyExpression;
import com.redactedopedia.factquery.api.expression.operator.Operator;

import java.io.IOException;
import java.math.BigDecimal;

public class ExpressionDeserializer extends JsonDeserializer<Expression> {

    @Override
    public Expression deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        if (node.has("fn")) {
            return new BivariateExpression(
                    Operator.fromCode(node.get("fn").asText()),
                    oc.treeToValue(node.get("a"), Expression.class),
                    oc.treeToValue(node.get("b"), Expression.class)
            );
        } else if (node.isBigDecimal()) {
            return new LiteralExpression(node.decimalValue());
        } else if (node.isInt()) {
            return new LiteralExpression(BigDecimal.valueOf(node.intValue()));
        } else if (node.isTextual()) {
            return new PropertyExpression(node.textValue());
        }
        return null;
    }
}


