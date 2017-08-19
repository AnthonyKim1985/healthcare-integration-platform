package org.bigdatacenter.healthcareintegrationplatform.service;

import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaColumnInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaDatabaseInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaTableInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrFilterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrProjectionInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrRequestInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrYearInfo;

import java.util.List;

public interface MetaDBService {
    TrRequestInfo findRequest(Integer dataSetUID);

    List<TrYearInfo> findYears(Integer dataSetUID);

    List<TrFilterInfo> findFilters(Integer dataSetUID);

    List<TrProjectionInfo> findProjections(Integer dataSetUID);

    List<TrProjectionInfo> findProjections(Integer dataSetUID, String etlEngName);

    MetaDatabaseInfo findDatabase(Integer edlIdx);

    MetaTableInfo findTable(Integer etlIdx);

    MetaColumnInfo findColumn(Integer eclIdx);

    List<MetaColumnInfo> findColumns(Integer edlIdx, String eclRef, Integer eclYear);

    List<String> findTableNames(Integer edlIdx, Integer tbYear);

    List<String> findColumnNames(String etlEngName);
}
