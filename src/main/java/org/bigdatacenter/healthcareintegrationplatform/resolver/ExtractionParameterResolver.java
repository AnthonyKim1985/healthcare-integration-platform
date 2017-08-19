package org.bigdatacenter.healthcareintegrationplatform.resolver;

import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter.ExtractionParameter;

public interface ExtractionParameterResolver {
    ExtractionParameter buildExtractionParameter(Integer dataSetUID);
}