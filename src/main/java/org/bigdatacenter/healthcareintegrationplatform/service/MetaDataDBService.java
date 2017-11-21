package org.bigdatacenter.healthcareintegrationplatform.service;

import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaColumnInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaDatabaseInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaTableInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrFilterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrProjectionInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrRequestInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrYearInfo;

import java.util.List;

public interface MetaDataDBService {
    /*
     * Transaction Database Service
     */
    TrRequestInfo findRequest(Integer dataSetUID);

    String findRequestForWorkFlow(Integer dataSetUID);

    List<TrYearInfo> findYears(Integer dataSetUID);

    List<TrFilterInfo> findFilters(Integer dataSetUID);

    List<TrProjectionInfo> findProjections(Integer dataSetUID);

    List<TrProjectionInfo> findProjections(Integer dataSetUID, String etlEngName);

    List<String> findProjectionNames(Integer dataSetUID, String etlEngName);

    Integer updateJobStartTime(Integer dataSetUID, String jobStartTime);

    Integer updateJobEndTime(Integer dataSetUID, String jobEndTime);

    Integer updateElapsedTime(Integer dataSetUID, String elapsedTime);

    Integer updateProcessState(Integer dataSetUID, Integer processState);

    Integer updateStatisticState(Integer dataSetUID, Integer statisticState);

    Integer createFtpInfo(Integer dataSetUID, String userID, String ftpURI);

    /*
     * Meta Database Service
     */
    MetaDatabaseInfo findDatabase(Integer edlIdx);

    MetaTableInfo findTable(Integer etlIdx);

    MetaColumnInfo findColumn(Integer eclIdx);

    List<MetaColumnInfo> findColumns(Integer edlIdx, String eclRef, Integer eclYear);

    List<String> findTableNames(Integer edlIdx, Integer tbYear);

    List<String> findColumnNames(String etlRef, Integer tbYear);
}
