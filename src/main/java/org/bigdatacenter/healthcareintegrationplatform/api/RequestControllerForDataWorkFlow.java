package org.bigdatacenter.healthcareintegrationplatform.api;

import org.bigdatacenter.healthcareintegrationplatform.domain.workflow.ScenarioTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/request/workflow/api")
public class RequestControllerForDataWorkFlow {
    private static final Logger logger = LoggerFactory.getLogger(RequestControllerForDataWorkFlow.class);
    private static final String currentThreadName = Thread.currentThread().getName();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "dataWorkFlow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // curl -v -X POST http://was.bigdatacenter.org:20780/request/workflow/api/dataWorkFlow -H "Content-Type:application/json; UTF-8" -d "{\"scenarioQueryList\":[{\"query\":\"SELECT * FROM T1\"},{\"query\":\"SELECT * FROM T2\"}]}"
    public String dataWorkFlow(@RequestBody ScenarioTask scenarioTask, HttpServletResponse httpServletResponse) {
        logger.info(String.format("%s - ScenarioTask: %s", currentThreadName, scenarioTask));
        final String jobAcceptedTime = dateFormat.format(new Date(System.currentTimeMillis()));
        return String.format("Accepted Time: %s", jobAcceptedTime);
    }
}