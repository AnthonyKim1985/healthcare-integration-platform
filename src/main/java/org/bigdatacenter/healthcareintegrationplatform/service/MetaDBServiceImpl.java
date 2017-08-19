package org.bigdatacenter.healthcareintegrationplatform.service;

import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaColumnInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaDatabaseInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaTableInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrFilterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrProjectionInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrRequestInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrYearInfo;
import org.bigdatacenter.healthcareintegrationplatform.persistence.MetaDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaDBServiceImpl implements MetaDBService {
    @Autowired
    private MetaDBMapper metaDBMapper;

    @Override
    public TrRequestInfo findRequest(Integer dataSetUID) {
        return metaDBMapper.readRequest(dataSetUID);
    }

    @Override
    public List<TrYearInfo> findYears(Integer dataSetUID) {
        return metaDBMapper.readYears(dataSetUID);
    }

    @Override
    public List<TrFilterInfo> findFilters(Integer dataSetUID) {
        return metaDBMapper.readFilters(dataSetUID);
    }

    @Override
    public List<TrProjectionInfo> findProjections(Integer dataSetUID) {
        return metaDBMapper.readProjections1(dataSetUID);
    }

    @Override
    public List<TrProjectionInfo> findProjections(Integer dataSetUID, String etlEngName) {
        return metaDBMapper.readProjections2(dataSetUID, etlEngName);
    }

    @Override
    public MetaDatabaseInfo findDatabase(Integer edlIdx) {
        return metaDBMapper.readDatabase(edlIdx);
    }

    @Override
    public MetaTableInfo findTable(Integer etlIdx) {
        return metaDBMapper.readTable(etlIdx);
    }

    @Override
    public MetaColumnInfo findColumn(Integer eclIdx) {
        return metaDBMapper.readColumn(eclIdx);
    }

    @Override
    public List<MetaColumnInfo> findColumns(Integer edlIdx, String eclRef, Integer eclYear) {
        return metaDBMapper.readColumns(edlIdx, eclRef, eclYear);
    }

    @Override
    public List<String> findTableNames(Integer edlIdx, Integer tbYear) {
        return metaDBMapper.readTableNames(edlIdx, tbYear);
    }

    @Override
    public List<String> findColumnNames(String etlEngName) {
        return metaDBMapper.readColumnNames(etlEngName);
    }
}