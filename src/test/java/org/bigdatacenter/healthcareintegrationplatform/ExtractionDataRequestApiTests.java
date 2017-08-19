package org.bigdatacenter.healthcareintegrationplatform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExtractionDataRequestApiTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testExtractionDataRequestApi() {
        String body = restTemplate.getForObject("/request/extraction/api/dataExtraction?dataSetUID=677", String.class);
        assertThat(body).isEqualTo("OK");
    }
}
