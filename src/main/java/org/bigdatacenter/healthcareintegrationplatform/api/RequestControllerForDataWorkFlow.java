package org.bigdatacenter.healthcareintegrationplatform.api;

import com.google.gson.Gson;
import org.bigdatacenter.healthcareintegrationplatform.domain.workflow.ScenarioTask;
import org.bigdatacenter.healthcareintegrationplatform.exception.RESTException;
import org.bigdatacenter.healthcareintegrationplatform.service.MetaDataDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.bigdatacenter.healthcareintegrationplatform.persistence.MetaDataDBMapper.PROCESS_STATE_CODE_REQUEST_ACCEPTED;

@RestController
@RequestMapping("/request/workflow/api")
public class RequestControllerForDataWorkFlow {
    private static final Logger logger = LoggerFactory.getLogger(RequestControllerForDataWorkFlow.class);
    private static final String currentThreadName = Thread.currentThread().getName();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Value("${scenario.processor.api.request.workflow}")
    private String scenarioURL;

    private final MetaDataDBService metaDataDBService;
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    @Autowired
    public RequestControllerForDataWorkFlow(MetaDataDBService metaDataDBService) {
        this.metaDataDBService = metaDataDBService;

        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "dataWorkFlow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // curl -v -X POST http://was.bigdatacenter.org:20780/request/workflow/api/dataWorkFlow -H "Content-Type:application/json; UTF-8" -d "{\"scenarioQueryList\":[{\"query\":\"SELECT * FROM T1\"},{\"query\":\"SELECT * FROM T2\"}]}"
    public String dataWorkFlow(@RequestBody ScenarioTask scenarioTask, HttpServletResponse httpServletResponse) {
        logger.info(String.format("%s - ScenarioTask: %s", currentThreadName, scenarioTask));

        try {
            final String response = restTemplate.postForObject(scenarioURL, scenarioTask, String.class);
            logger.info(String.format("%s - response: %s", currentThreadName, response));

            return String.format("Accepted Time: %s", dateFormat.format(new Date(System.currentTimeMillis())));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "dataWorkFlow", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String dataWorkFlow(@RequestParam Integer dataSetUID, HttpServletResponse httpServletResponse) {
        logger.info(String.format("%s - dataSetUID: %d", currentThreadName, dataSetUID));

        try {
            final String jsonForWorkFlowQueries = metaDataDBService.findRequestForWorkFlow(dataSetUID);
            final ScenarioTask scenarioTask = (new Gson()).fromJson(jsonForWorkFlowQueries, ScenarioTask.class);
            logger.info(String.format("%s - ScenarioTask: %s", currentThreadName, scenarioTask));

            final MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("scenarioTask", scenarioTask);
            parameters.add("dataSetUID", dataSetUID);

            final HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parameters, headers);
            final String response = restTemplate.postForObject(scenarioURL, request, String.class);
            logger.info(String.format("%s - response: %s", currentThreadName, response));

            metaDataDBService.updateProcessState(dataSetUID, PROCESS_STATE_CODE_REQUEST_ACCEPTED);

            return String.format("Accepted Time: %s", dateFormat.format(new Date(System.currentTimeMillis())));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
    }
}