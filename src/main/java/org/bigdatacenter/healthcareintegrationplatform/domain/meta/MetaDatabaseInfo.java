package org.bigdatacenter.healthcareintegrationplatform.domain.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaDatabaseInfo implements Serializable {
    private Integer edl_idx;
    private String edl_org_name;
    private String edl_kor_name;
    private String edl_eng_name;
    private Integer edl_indicator_yn;
    private Integer orgID;
}
