package org.bigdatacenter.healthcareintegrationplatform.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrProjectionInfo implements Serializable {
    private Integer selectUID;
    private Integer dataSetUID;
    private Integer edl_idx;
    private String ecl_year;
    private String etl_eng_name;
    private String ecl_eng_name;
}
