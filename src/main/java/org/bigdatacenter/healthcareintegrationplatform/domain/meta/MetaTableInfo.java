package org.bigdatacenter.healthcareintegrationplatform.domain.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaTableInfo implements Serializable {
    private Integer etl_idx;
    private Integer edl_idx;
    private String etl_eng_name;
    private String etl_kor_name;
    private Integer tb_year;
    private String etl_ori_kor_name;
    private String etl_ori_eng_name;
    private String etl_ref;
}
