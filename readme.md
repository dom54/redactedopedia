# Running the application
Run Application.class - its a spring boot app and will bind to port 8080

Send a POST request to the endpoint at /fact/query

# Run via maven
mvn spring-boot:run

# Running the integration tests
Run FactQueryControllerIT for an end to end test


# Key classes
FactQueryController - REST endpoint  

ExpressionDeserializer - handles the polymorphic types  

Operator - enum describing supported operations  

QueryService - applies the visitor pattern to process the JSON tree  

CsvSecurityDataRepository - loads up the CSV data