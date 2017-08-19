package org.bigdatacenter.healthcareintegrationplatform;

import org.bigdatacenter.healthcareintegrationplatform.service.MetaDBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthcareIntegrationPlatformApplicationTests {
    @Autowired
    private MetaDBService metaDBService;

    @Test
    public void contextLoads() {
        assertThat(metaDBService.findRequest(200).getDataSetUID(), is(200));
    }
}