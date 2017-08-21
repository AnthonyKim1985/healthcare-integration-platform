package org.bigdatacenter.healthcareintegrationplatform.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaColumnInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaDatabaseInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaTableInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrFilterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrProjectionInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrRequestInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrYearInfo;

import java.util.List;

@Mapper
public interface MetaDataDBMapper {
    /*
     * Transaction Database Mapper
     */
    @Select("SELECT * FROM health_care_ui.tr_dataset_list WHERE dataSetUID = #{dataSetUID}")
    TrRequestInfo readRequest(@Param("dataSetUID") Integer dataSetUID);

    @Select("SELECT * FROM health_care_ui.tr_dataset_year WHERE dataSetUID = #{dataSetUID}")
    List<TrYearInfo> readYears(@Param("dataSetUID") Integer dataSetUID);

    @Select("SELECT * FROM health_care_ui.tr_dataset_filter WHERE dataSetUID = #{dataSetUID}")
    List<TrFilterInfo> readFilters(@Param("dataSetUID") Integer dataSetUID);

    @Select("SELECT * FROM health_care_ui.tr_dataset_select WHERE dataSetUID = #{dataSetUID}")
    List<TrProjectionInfo> readProjections1(@Param("dataSetUID") Integer dataSetUID);

    @Select("SELECT * FROM tr_dataset_select WHERE dataSetUID = #{dataSetUID} AND etl_eng_name = #{etl_eng_name}")
    List<TrProjectionInfo> readProjections2(@Param("dataSetUID") Integer dataSetUID, @Param("etl_eng_name") String etlEngName);


    /*
     * Meta Database Mapper
     */
    @Select("SELECT * FROM health_care_ui.chu_db_list WHERE edl_idx = #{edl_idx}")
    MetaDatabaseInfo readDatabase(@Param("edl_idx") Integer edlIdx);

    @Select("SELECT * FROM health_care_ui.chu_tb_list WHERE etl_idx = #{etl_idx}")
    MetaTableInfo readTable(@Param("etl_idx") Integer etlIdx);

    @Select("SELECT * FROM health_care_ui.chu_col_list_ref WHERE ecl_idx = #{ecl_idx}")
    MetaColumnInfo readColumn(@Param("ecl_idx") Integer eclIdx);

    @Select("SELECT * FROM health_care_ui.chu_col_list_ref WHERE edl_idx = #{edl_idx} AND ecl_ref = #{ecl_ref} AND ecl_year = #{ecl_year}")
    List<MetaColumnInfo> readColumns(@Param("edl_idx") Integer edlIdx, @Param("ecl_ref") String eclRef, @Param("ecl_year") Integer eclYear);

    @Select("SELECT etl_eng_name FROM health_care_ui.chu_tb_list WHERE edl_idx = #{edl_idx} AND tb_year = #{tb_year}")
    List<String> readTableNames(@Param("edl_idx") Integer edlIdx, @Param("tb_year") Integer tbYear);

    @Select("SELECT DISTINCT ecl_eng_name FROM health_care_ui.chu_col_list WHERE etl_idx = (SELECT etl_idx FROM health_care_ui.chu_tb_list WHERE etl_eng_name = #{etl_eng_name})")
    List<String> readColumnNames(@Param("etl_eng_name") String etlEngName);
}