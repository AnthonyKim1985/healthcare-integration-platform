package org.bigdatacenter.healthcareintegrationplatform.api;

import org.bigdatacenter.healthcareintegrationplatform.domain.workflow.ScenarioTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    @RequestMapping(value = "dataWorkFlow", method = RequestMethod.POST)
    public String dataWorkFlow(@RequestParam ScenarioTask scenarioTask, HttpServletResponse httpServletResponse) {
        logger.info(String.format("%s - ScenarioTask: %s", currentThreadName, scenarioTask));
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        final String jobAcceptedTime = dateFormat.format(new Date(System.currentTimeMillis()));
        return String.format("Accepted Time: %s", jobAcceptedTime);
    }
}