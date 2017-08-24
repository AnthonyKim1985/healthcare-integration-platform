package org.bigdatacenter.healthcareintegrationplatform.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrFilterInfo implements Serializable {
    private Integer filterUID;
    private Integer dataSetUID;
    private String filterName;
    private String filterEngName;
    private String filterValues;
    private String filterOperator;
}
