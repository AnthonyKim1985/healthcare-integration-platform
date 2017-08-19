package org.bigdatacenter.healthcareintegrationplatform.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrYearInfo implements Serializable {
    private Integer clickYearUID;
    private Integer dataSetUID;
    private Integer yearName;
}
