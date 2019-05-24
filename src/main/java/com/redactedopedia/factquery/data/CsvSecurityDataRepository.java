package com.redactedopedia.factquery.data;

import com.redactedopedia.factquery.api.data.SecurityDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CsvSecurityDataRepository implements SecurityDataRepository {

    @Value("classpath:attributes.csv")
    private Resource attributes;

    @Value("classpath:facts.csv")
    private Resource facts;

    @Value("classpath:securities.csv")
    private Resource securities;
    
    private Map<String, Security>  symbolToSecurity;

    @PostConstruct
    public void init() throws IOException {
        Map<Integer, String> securityIdToSymbol = createSecurities();
        Map<Integer, String> attributeIdToProperty = createAttributes();
        populateSecurityCache(securityIdToSymbol, attributeIdToProperty);
    }

    private Map<Integer, String> createSecurities() throws IOException {
        Map<Integer,String> returnMap = new HashMap<>();
        Files.readAllLines(Paths.get(securities.getURI()))
                .stream().skip(1).map(s -> s.split(","))
                .forEach(line -> {
                    returnMap.put(Integer.parseInt(line[0]),line[1]);
        });
        return returnMap;
    }

    private void populateSecurityCache(Map<Integer, String> securityIdToSymbol, Map<Integer, String> attributeIdToProperty) throws IOException {
        Map<String, Security> securities = securityIdToSymbol.entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getValue(),
                entry -> new Security(entry.getKey(), entry.getValue())));

        Files.readAllLines(Paths.get(facts.getURI()))
                .stream().skip(1).map(s -> s.split(","))
                .forEach(line -> {
                    Integer securityId = Integer.parseInt(line[0]);
                    Integer attributeId = Integer.parseInt(line[1]);
                    BigDecimal value = new BigDecimal(line[2]);

                    String symbol = securityIdToSymbol.get(securityId);
                    String attributeName = attributeIdToProperty.get(attributeId);

                    if (symbol!=null && attributeName!=null && securities.containsKey(symbol)) {
                        Security security = securities.get(symbol);
                        security.addFact(attributeName,value);
                    }
                }
        );
        this.symbolToSecurity = securities;

    }

    private Map<Integer, String> createAttributes() throws IOException {
        Map<Integer,String> returnMap = new HashMap<>();
        Files.readAllLines(Paths.get(attributes.getURI()))
                .stream().skip(1).map(s -> s.split(","))
                .forEach(line -> {
                    returnMap.put(Integer.parseInt(line[0]),line[1]);
                });
        return returnMap;
    }


    @Override
    public BigDecimal getFactForSecurity(String symbol, String property) {
        Security security = symbolToSecurity.get(symbol);
        return security!=null?security.getFact(property):null;
    }

    Security getSecurity(String symbol) {
        return symbolToSecurity.get(symbol);
    }

    long countSecurities() {
        return symbolToSecurity.size();
    }

}
