package org.bigdatacenter.healthcareintegrationplatform.domain.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaColumnInfo implements Serializable {
    private Integer ecl_idx;
    private Integer edl_idx;
    private Integer etl_idx;
    private String ecl_kor_name;
    private String ecl_eng_name;
    private Integer ecl_search_type;
    private Integer is_usable;
    private Integer ecl_year;
    private String ecl_ref;
    private Integer ecl_default_check;
    private Integer column_type;
}
