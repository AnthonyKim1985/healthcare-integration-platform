package org.bigdatacenter.healthcareintegrationplatform.api;

import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter.ExtractionParameter;
import org.bigdatacenter.healthcareintegrationplatform.exception.RESTException;
import org.bigdatacenter.healthcareintegrationplatform.resolver.ExtractionParameterResolver;
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

    @Value("${request.hira}")
    private String hiraURL;

    @Value("${request.nhic}")
    private String nhicURL;

    @Value("${request.cdc}")
    private String cdcURL;

    @Value("${request.kihasa}")
    private String kihasaURL;

    private final ExtractionParameterResolver extractionParameterResolver;

    @Autowired
    public RequestControllerForDataExtraction(ExtractionParameterResolver extractionParameterResolver) {
        this.extractionParameterResolver = extractionParameterResolver;
    }

    @RequestMapping(value = "dataExtraction", method = RequestMethod.GET)
    public String dataExtraction(@RequestParam Integer dataSetUID, HttpServletResponse httpServletResponse) {
        try {
            final ExtractionParameter extractionParameter = extractionParameterResolver.buildExtractionParameter(dataSetUID);
            logger.info(String.format("%s", extractionParameter.toString()));
            final HttpEntity<MultiValueMap<String, ExtractionParameter>> httpEntityForRequest = getHttpEntityForRequest(extractionParameter);

            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            final String responseMessage;
            final Integer dataSetID = extractionParameter.getRequestInfo().getDatasetID();
            switch (dataSetID) {
                case NPS:
                case NIS:
                case PPS:
                case APS:
                    // TODO: 건강보험심사평가원에 데이터 추출 요청을 수행한다.
                    responseMessage = restTemplate.postForObject(hiraURL, httpEntityForRequest, String.class);
                    logger.info(String.format("%s - Response From HIRA: %s", currentThreadName, responseMessage));
                    break;
                case NHIC:
                    // TODO: 국민건강보험공단에 데이터 추출 요청을 수행한다.
                    responseMessage = restTemplate.postForObject(nhicURL, httpEntityForRequest, String.class);
                    logger.info(String.format("%s - Response From NHIC: %s", currentThreadName, responseMessage));
                    break;
                case KHP_HH:
                case KHP_IND:
                    // TODO: 한국보건사회연구원에 데이터 추출 요청을 수행한다.
                    responseMessage = restTemplate.postForObject(kihasaURL, httpEntityForRequest, String.class);
                    logger.info(String.format("%s - Response From KIHASA: %s", currentThreadName, responseMessage));
                    break;
                case KOGES:
                case CHS:
                case KNHANES:
                    // TODO: 질병관리본부에 데이터 추출 요청을 수행한다.
                    responseMessage = restTemplate.postForObject(cdcURL, httpEntityForRequest, String.class);
                    logger.info(String.format("%s - Response From CDC: %s", currentThreadName, responseMessage));
                    break;
                default:
                    final String errorMessage = String.format("Invalid dataSetID: %d", dataSetID);
                    throw new RESTException(errorMessage, httpServletResponse);
            }
            return responseMessage;
        } catch (Exception e) {
            throw new RESTException(e.getMessage(), httpServletResponse);
        }
    }

    private HttpEntity<MultiValueMap<String, ExtractionParameter>> getHttpEntityForRequest(ExtractionParameter extractionParameter) {
        final MultiValueMap<String, ExtractionParameter> parameters = new LinkedMultiValueMap<>();
        parameters.add("extractionParameter", extractionParameter);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return new HttpEntity<>(parameters, headers);
    }


    @RequestMapping(value = "acknowledgement", method = RequestMethod.GET)
    public void dataExtractionAck(@RequestParam String dataSetUID, HttpServletResponse httpServletResponse) {

    }
}