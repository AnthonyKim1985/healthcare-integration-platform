package org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter.info.ParameterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrRequestInfo;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ExtractionParameter implements Serializable {
    private TrRequestInfo requestInfo;
    private List<ParameterInfo> parameterInfoList;
}