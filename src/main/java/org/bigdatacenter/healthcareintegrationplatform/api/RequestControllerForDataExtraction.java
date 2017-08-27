package org.bigdatacenter.healthcareintegrationplatform.api;

import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter.ExtractionParameter;
import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.response.ExtractionResponse;
import org.bigdatacenter.healthcareintegrationplatform.exception.RESTException;
import org.bigdatacenter.healthcareintegrationplatform.resolver.ExtractionParameterResolver;
import org.bigdatacenter.healthcareintegrationplatform.service.MetaDataDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/request/extraction/api")
public class RequestControllerForDataExtraction {
    private static final Logger logger = LoggerFactory.getLogger(RequestControllerForDataExtraction.class);
    private static final String currentThreadName = Thread.currentThread().getName();

    private static final int NPS = 1;
    private static final int NIS = 2;
    private static final int PPS = 3;
    private static final int APS = 4;
    private static final int NHIC = 5;
    private static final int CHS = 6;
    private static final int KNHANES = 7;
    private static final int KHP_HH = 8;
    private static final int KOGES = 9;
    private static final int KHP_IND = 10;

    @Value("${nhic.rest.api.request.extraction}")
    private String nhicURL;

    @Value("${hira.rest.api.request.extraction}")
    private String hiraURL;

    @Value("${kihasa.rest.api.request.extraction}")
    private String kihasaURL;

    @Value("${cdc.general.rest.api.request.extraction}")
    private String cdcGeneralURL;

    @Value("${cdc.koges.rest.api.request.extraction}")
    private String cdcKogesURL;

    private final MetaDataDBService metaDataDBService;

    private final ExtractionParameterResolver extractionParameterResolver;

    @Autowired
    public RequestControllerForDataExtraction(ExtractionParameterResolver extractionParameterResolver, MetaDataDBService metaDataDBService) {
        this.extractionParameterResolver = extractionParameterResolver;
        this.metaDataDBService = metaDataDBService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "dataExtraction", method = RequestMethod.GET)
    public String dataExtraction(@RequestParam Integer dataSetUID, HttpServletResponse httpServletResponse) {
        try {
            final ExtractionParameter extractionParameter = extractionParameterResolver.buildExtractionParameter(dataSetUID);
            logger.info(String.format("%s - extractionParameter: %s", currentThreadName, extractionParameter));

            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            final ExtractionResponse extractionResponse;
            final Integer dataSetID = extractionParameter.getRequestInfo().getDatasetID();
            switch (dataSetID) {
                case NPS:
                case NIS:
                case PPS:
                case APS:
                    // TODO: 건강보험심사평가원에 데이터 추출 요청을 수행한다.
                    extractionResponse = restTemplate.postForObject(hiraURL, extractionParameter, ExtractionResponse.class);
                    logger.info(String.format("%s - Response From HIRA: %s", currentThreadName, extractionResponse));
                    break;
                case NHIC:
                    // TODO: 국민건강보험공단에 데이터 추출 요청을 수행한다.
                    extractionResponse = restTemplate.postForObject(nhicURL, extractionParameter, ExtractionResponse.class);
                    logger.info(String.format("%s - Response From NHIC: %s", currentThreadName, extractionResponse));
                    break;
                case KHP_HH:
                case KHP_IND:
                    // TODO: 한국보건사회연구원에 데이터 추출 요청을 수행한다. (의료패널 데이터)
                    extractionResponse = restTemplate.postForObject(kihasaURL, extractionParameter, ExtractionResponse.class);
                    logger.info(String.format("%s - Response From KIHASA: %s", currentThreadName, extractionResponse));
                    break;
                case CHS:
                case KNHANES:
                    // TODO: 질병관리본부에 데이터 추출 요청을 수행한다. (지역사회건강조사, 국민건강여양조사)
                    extractionResponse = restTemplate.postForObject(cdcGeneralURL, extractionParameter, ExtractionResponse.class);
                    logger.info(String.format("%s - Response From CDC: %s", currentThreadName, extractionResponse));
                    break;
                case KOGES:
                    // TODO: 질병관리본부에 데이터 추출 요청을 수행한다. (유전체)
                    extractionResponse = restTemplate.postForObject(cdcKogesURL, extractionParameter, ExtractionResponse.class);
                    logger.info(String.format("%s - Response From CDC: %s", currentThreadName, extractionResponse));
                    break;

                default:
                    final String errorMessage = String.format("Invalid dataSetID: %d", dataSetID);
                    throw new RESTException(errorMessage, httpServletResponse);
            }

            return String.format("Job Accepted Time: %s", extractionResponse.getJobAcceptTime());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "updateJobStartTime", method = RequestMethod.POST)
    public Integer updateJobStartTime(@RequestParam Integer dataSetUID, @RequestParam String jobStartTime, HttpServletResponse httpServletResponse) {
        final Integer updateRowCount;
        try {
            updateRowCount = metaDataDBService.updateJobStartTime(dataSetUID, jobStartTime);
        } catch (Exception e) {
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
        return updateRowCount;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "updateJobEndTime", method = RequestMethod.POST)
    public Integer updateJobEndTime(@RequestParam Integer dataSetUID, @RequestParam String jobEndTime, HttpServletResponse httpServletResponse) {
        final Integer updateRowCount;
        try {
            updateRowCount = metaDataDBService.updateJobEndTime(dataSetUID, jobEndTime);
        } catch (Exception e) {
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
        return updateRowCount;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "updateElapsedTime", method = RequestMethod.POST)
    public Integer updateElapsedTime(@RequestParam Integer dataSetUID, @RequestParam String elapsedTime, HttpServletResponse httpServletResponse) {
        final Integer updateRowCount;
        try {
            updateRowCount = metaDataDBService.updateElapsedTime(dataSetUID, elapsedTime);
        } catch (Exception e) {
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
        return updateRowCount;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "updateProcessState", method = RequestMethod.POST)
    public Integer updateProcessState(@RequestParam Integer dataSetUID, @RequestParam Integer processState, HttpServletResponse httpServletResponse) {
        final Integer updateRowCount;
        try {
            updateRowCount = metaDataDBService.updateProcessState(dataSetUID, processState);
        } catch (Exception e) {
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
        return updateRowCount;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "createFtpInfo", method = RequestMethod.POST)
    public Integer createFtpInfo(@RequestParam Integer dataSetUID, @RequestParam String userID, @RequestParam String ftpURI, HttpServletResponse httpServletResponse) {
        final Integer retValue;
        try {
            retValue = metaDataDBService.createFtpInfo(dataSetUID, userID, ftpURI);
        } catch (Exception e) {
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
        return retValue;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "readProjectionNames", method = RequestMethod.POST)
    public String readProjectionNames(@RequestParam Integer dataSetUID, @RequestParam String tableName, HttpServletResponse httpServletResponse) {
        StringBuilder projectionNameBuilder = new StringBuilder();
        try {
            List<String> projectionNameList = metaDataDBService.findProjectionNames(dataSetUID, tableName);

            if (projectionNameList == null || projectionNameList.isEmpty())
                projectionNameList = metaDataDBService.findColumnNames(tableName);

            for (int i = 0; i < projectionNameList.size(); i++) {
                String projectionName = projectionNameList.get(i);

                projectionNameBuilder.append(projectionName);
                if (i < projectionNameList.size() - 1)
                    projectionNameBuilder.append(',');
            }
        } catch (Exception e) {
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
        return projectionNameBuilder.toString();
    }
}