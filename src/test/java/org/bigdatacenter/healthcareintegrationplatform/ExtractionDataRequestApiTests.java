package org.bigdatacenter.healthcareintegrationplatform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testReadProjectionNames() {
        final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("dataSetUID", "732");
        parameters.add("tableName", "nis_t40_2011");
        parameters.add("tableYear", "2011");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
        String response = restTemplate.postForObject("/request/extraction/api/readProjectionNames", request, String.class);

        assertThat(response).isEqualTo("spec_id_sno,sick_sno,sick_dgsbjt_cd,sick_cd,dmd_dgsbjt_cd,year,data_set,jid,sex_tp_cd,agg,dif,recu_fr_dd,sido_cd,cl_cd,org_df");
    }
}