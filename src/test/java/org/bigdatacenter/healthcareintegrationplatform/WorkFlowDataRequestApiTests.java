package org.bigdatacenter.healthcareintegrationplatform;

import com.google.gson.Gson;
import org.bigdatacenter.healthcareintegrationplatform.domain.workflow.ScenarioQuery;
import org.bigdatacenter.healthcareintegrationplatform.domain.workflow.ScenarioTask;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkFlowDataRequestApiTests {
//    @Autowired
//    private TestRestTemplate restTemplate;


    @Test
    public void testObject() {
        List<ScenarioQuery> queries = new LinkedList<>();
        queries.add(new ScenarioQuery("SELECT * FROM T1"));
        queries.add(new ScenarioQuery("SELECT * FROM T2"));

        Gson gson = new Gson();
        String json = gson.toJson(new ScenarioTask(queries), ScenarioTask.class);

        assertThat(json, is("{\"scenarioQueryList\":[{\"query\":\"SELECT * FROM T1\"},{\"query\":\"SELECT * FROM T2\"}]}"));
    }
}
