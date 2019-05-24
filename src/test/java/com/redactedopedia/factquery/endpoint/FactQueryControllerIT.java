package com.redactedopedia.factquery.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redactedopedia.factquery.api.FactQueryResult;
import com.redactedopedia.factquery.app.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class FactQueryControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/fact/query");
    }

    @Test
    public void regular_query_should_return_response() throws Exception {
        JsonNode jsonNode = readFrom("./complex_query.json");
        ResponseEntity<FactQueryResult> response = template.postForEntity(base.toString(), jsonNode, FactQueryResult.class);

        assertThat(response.getBody().getResult().doubleValue(), equalTo(-44d));
    }

    @Test
    public void scenario_from_spec1_should_return_response() throws Exception {
        JsonNode jsonNode = readFrom("./scenario_from_spec_query.json");
        ResponseEntity<FactQueryResult> response = template.postForEntity(base.toString(), jsonNode, FactQueryResult.class);

        assertThat(response.getBody().getResult().doubleValue(), equalTo(8d));
    }

    @Test
    public void scenario_from_spec2_should_return_response() throws Exception {
        JsonNode jsonNode = readFrom("./scenario_from_spec_query2.json");
        ResponseEntity<FactQueryResult> response = template.postForEntity(base.toString(), jsonNode, FactQueryResult.class);

        assertThat(response.getBody().getResult().doubleValue(), equalTo(0.5d));
    }

    @Test
    public void scenario_from_spec3_should_return_response() throws Exception {
        JsonNode jsonNode = readFrom("./scenario_from_spec_query3.json");
        ResponseEntity<FactQueryResult> response = template.postForEntity(base.toString(), jsonNode, FactQueryResult.class);
        //(eps - shares) - (assets-liabilities)
        // (6 - 30) - (21-24)
        assertThat(response.getBody().getResult().doubleValue(), equalTo(-21d));
    }

    @Test
    public void simple_query_should_return_response() throws Exception {
        JsonNode jsonNode = readFrom("./simple_query.json");
        ResponseEntity<FactQueryResult> response = template.postForEntity(base.toString(), jsonNode, FactQueryResult.class);

        assertThat(response.getBody().getResult().doubleValue(), equalTo(6d));
    }


    private JsonNode readFrom(String location) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource stateFile = new ClassPathResource(location);
        return objectMapper.readTree(stateFile.getURL());

    }
}