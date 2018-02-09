package org.bigdatacenter.healthcareintegrationplatform.service;

import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaColumnInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaDatabaseInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaTableInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrFilterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrProjectionInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrRequestInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrYearInfo;
import org.bigdatacenter.healthcareintegrationplatform.persistence.MetaDataDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaDataDBServiceImpl implements MetaDataDBService {
    @Autowired
    private MetaDataDBMapper metaDataDBMapper;

    @Override
    public TrRequestInfo findRequest(Integer dataSetUID) {
        try {
            return metaDataDBMapper.readRequest(dataSetUID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String findRequestForWorkFlow(Integer dataSetUID) {
        try {
            return metaDataDBMapper.readRequestForWorkFlow(dataSetUID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrYearInfo> findYears(Integer dataSetUID) {
        try {
            return metaDataDBMapper.readYears(dataSetUID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrFilterInfo> findFilters(Integer dataSetUID) {
        try {
            return metaDataDBMapper.readFilters(dataSetUID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrProjectionInfo> findProjections(Integer dataSetUID) {
        try {
            return metaDataDBMapper.readProjections1(dataSetUID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrProjectionInfo> findProjections(Integer dataSetUID, String etlEngName) {
        return metaDataDBMapper.readProjections2(dataSetUID, etlEngName);
    }

    @Override
    public List<String> findProjectionNames(Integer dataSetUID, String etlEngName) {
        return metaDataDBMapper.readProjectionNames(dataSetUID, etlEngName);
    }

    @Override
    public Integer updateJobStartTime(Integer dataSetUID, String jobStartTime) {
        return metaDataDBMapper.updateJobStartTime(dataSetUID, jobStartTime);
    }

    @Override
    public Integer updateJobEndTime(Integer dataSetUID, String jobEndTime) {
        return metaDataDBMapper.updateJobEndTime(dataSetUID, jobEndTime);
    }

    @Override
    public Integer updateElapsedTime(Integer dataSetUID, String elapsedTime) {
        return metaDataDBMapper.updateElapsedTime(dataSetUID, elapsedTime);
    }

    @Override
    public Integer updateProcessState(Integer dataSetUID, Integer processState) {
        return metaDataDBMapper.updateProcessState(dataSetUID, processState);
    }

    @Override
    public Integer updateStatisticState(Integer dataSetUID, Integer statisticState) {
        return metaDataDBMapper.updateStatisticState(dataSetUID, statisticState);
    }

    @Override
    public Integer createFtpInfo(Integer dataSetUID, String userID, String ftpURI) {
        return metaDataDBMapper.createFtpInfo(dataSetUID, userID, ftpURI);
    }

    @Override
    public MetaDatabaseInfo findDatabase(Integer edlIdx) {
        return metaDataDBMapper.readDatabase(edlIdx);
    }

    @Override
    public MetaTableInfo findTable(Integer etlIdx) {
        return metaDataDBMapper.readTable(etlIdx);
    }

    @Override
    public MetaColumnInfo findColumn(Integer eclIdx) {
        return metaDataDBMapper.readColumn(eclIdx);
    }

    @Override
    public List<MetaColumnInfo> findColumns(Integer edlIdx, String eclRef, Integer eclYear) {
        return metaDataDBMapper.readColumns(edlIdx, eclRef, eclYear);
    }

    @Override
    public List<String> findTableNames(Integer edlIdx, Integer tbYear) {
        return metaDataDBMapper.readTableNames(edlIdx, tbYear);
    }

    @Override
    public List<String> findColumnNames(String etlRef, Integer tbYear) {
        return metaDataDBMapper.readColumnNames(etlRef, tbYear);
    }
}