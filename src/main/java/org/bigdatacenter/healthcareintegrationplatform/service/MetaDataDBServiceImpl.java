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
        try {
            return metaDataDBMapper.readProjections2(dataSetUID, etlEngName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<String> findProjectionNames(Integer dataSetUID, String etlEngName) {
        try {
            return metaDataDBMapper.readProjectionNames(dataSetUID, etlEngName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer updateJobStartTime(Integer dataSetUID, String jobStartTime) {
        try {
            return metaDataDBMapper.updateJobStartTime(dataSetUID, jobStartTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer updateJobEndTime(Integer dataSetUID, String jobEndTime) {
        try {
            return metaDataDBMapper.updateJobEndTime(dataSetUID, jobEndTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer updateElapsedTime(Integer dataSetUID, String elapsedTime) {
        try {
            return metaDataDBMapper.updateElapsedTime(dataSetUID, elapsedTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer updateProcessState(Integer dataSetUID, Integer processState) {
        try {
            return metaDataDBMapper.updateProcessState(dataSetUID, processState);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer updateStatisticState(Integer dataSetUID, Integer statisticState) {
        try {
            return metaDataDBMapper.updateStatisticState(dataSetUID, statisticState);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer createFtpInfo(Integer dataSetUID, String userID, String ftpURI) {
        try {
            return metaDataDBMapper.createFtpInfo(dataSetUID, userID, ftpURI);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MetaDatabaseInfo findDatabase(Integer edlIdx) {
        try {
            return metaDataDBMapper.readDatabase(edlIdx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MetaTableInfo findTable(Integer etlIdx) {
        try {
            return metaDataDBMapper.readTable(etlIdx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MetaColumnInfo findColumn(Integer eclIdx) {
        try {
            return metaDataDBMapper.readColumn(eclIdx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<MetaColumnInfo> findColumns(Integer edlIdx, String eclRef, Integer eclYear) {
        try {
            return metaDataDBMapper.readColumns(edlIdx, eclRef, eclYear);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
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