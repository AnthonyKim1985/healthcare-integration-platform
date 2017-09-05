package org.bigdatacenter.healthcareintegrationplatform.resolver;

import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter.ExtractionParameter;
import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter.info.AdjacentTableInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.extraction.parameter.info.ParameterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaColumnInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaDatabaseInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.meta.MetaTableInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrFilterInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrProjectionInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrRequestInfo;
import org.bigdatacenter.healthcareintegrationplatform.domain.transaction.TrYearInfo;
import org.bigdatacenter.healthcareintegrationplatform.service.MetaDataDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ExtractionParameterResolverImpl implements ExtractionParameterResolver {
    private static final Logger logger = LoggerFactory.getLogger(ExtractionParameterResolverImpl.class);
    private static final String currentThreadName = Thread.currentThread().getName();

    private final MetaDataDBService metaDataDBService;

    @Autowired
    public ExtractionParameterResolverImpl(MetaDataDBService metaDataDBService) {
        this.metaDataDBService = metaDataDBService;
    }

    @Value("${adjacent.table.excluded.targets}")
    private String excludedAdjacentTableTargets;

    @Override
    public ExtractionParameter buildExtractionParameter(Integer dataSetUID) {
        final List<ParameterInfo> parameterInfoList = new ArrayList<>();

        try {
            final TrRequestInfo requestInfo = metaDataDBService.findRequest(dataSetUID);
            if (requestInfo == null)
                throw new NullPointerException(String.format("%s - RequestInfo not found", currentThreadName));

            final List<TrYearInfo> yearInfoList = metaDataDBService.findYears(dataSetUID);
            if (yearInfoList == null)
                throw new NullPointerException(String.format("%s - RequestYearInfo not found", currentThreadName));

            final List<TrFilterInfo> filterInfoList = metaDataDBService.findFilters(dataSetUID);
            if (filterInfoList == null)
                throw new NullPointerException(String.format("%s - FilterInfo not found", currentThreadName));

            final MetaDatabaseInfo databaseInfo = metaDataDBService.findDatabase(requestInfo.getDatasetID());
            if (databaseInfo == null)
                throw new NullPointerException(String.format("%s - Meta Database not found", currentThreadName));

            final Set<AdjacentTableInfo> adjacentTableInfoSet = new HashSet<>();

            for (TrYearInfo yearInfo : yearInfoList) {
                final Integer dataSetYear = yearInfo.getYearName();
                List<String> tableNameList = metaDataDBService.findTableNames(databaseInfo.getEdl_idx(), yearInfo.getYearName());
                for (String tableName : tableNameList)
                    if (isAdjacentTableTarget(tableName))
                        adjacentTableInfoSet.add(new AdjacentTableInfo(dataSetYear, databaseInfo.getEdl_eng_name(), tableName, getTableHeader(tableName, dataSetYear, dataSetUID)));

                for (TrFilterInfo filterInfo : filterInfoList) {
                    List<MetaColumnInfo> metaColumnInfoList = metaDataDBService.findColumns(requestInfo.getDatasetID(), filterInfo.getFilterEngName(), dataSetYear);
                    logger.debug(String.format("%s - %s", currentThreadName, metaColumnInfoList));

                    for (MetaColumnInfo metaColumnInfo : metaColumnInfoList) {
                        final MetaTableInfo metaTableInfo = metaDataDBService.findTable(metaColumnInfo.getEtl_idx());
                        if (metaTableInfo == null)
                            throw new NullPointerException(String.format("%s - The meta information for the table could not be found. (etl_idx: %d)", currentThreadName, metaColumnInfo.getEtl_idx()));

                        final String filterValues = filterInfo.getFilterValues();
                        if (filterValues == null)
                            throw new NullPointerException(String.format("%s - FilterValue is null", currentThreadName));

                        final String filterOperator = filterInfo.getFilterOperator();
                        if (filterOperator == null)
                            throw new NullPointerException(String.format("%s - FilterOperator is null", currentThreadName));

                        parameterInfoList.add(new ParameterInfo(
                                dataSetYear, databaseInfo.getEdl_eng_name(), metaTableInfo.getEtl_eng_name(),
                                metaColumnInfo.getColumn_type(), metaColumnInfo.getEcl_eng_name(), filterValues,
                                filterOperator, getTableHeader(metaTableInfo.getEtl_eng_name(), metaTableInfo.getTb_year(), dataSetUID)));
                    }
                }
            }

            return new ExtractionParameter(databaseInfo.getEdl_eng_name(), requestInfo, parameterInfoList, adjacentTableInfoSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    private Boolean isAdjacentTableTarget(String tableName) {
        for (String adjacentTableTarget : excludedAdjacentTableTargets.split("[,]"))
            if (tableName.contains(adjacentTableTarget))
                return Boolean.FALSE;

        return Boolean.TRUE;
    }

    private String getTableHeader(String tableName, Integer tableYear, Integer dataSetUID) {
        final StringBuilder headerBuilder = new StringBuilder();

        final List<TrProjectionInfo> projectionInfoList = metaDataDBService.findProjections(dataSetUID, tableName);
        if (projectionInfoList == null || projectionInfoList.isEmpty()) {
            final List<String> columnNameList = metaDataDBService.findColumnNames(tableName, tableYear);
            if (columnNameList == null || columnNameList.isEmpty())
                throw new NullPointerException(String.format("%s - The column list meta data for tableName %s not exists.", currentThreadName, tableName));

            final Integer columnNameListSize = columnNameList.size();
            for (int i = 0; i < columnNameListSize; i++) {
                headerBuilder.append(columnNameList.get(i).trim());
                if (i < columnNameListSize - 1)
                    headerBuilder.append(',');
            }
        } else {
            final Integer metaSelectedColumnInfoListSize = projectionInfoList.size();
            for (int i = 0; i < metaSelectedColumnInfoListSize; i++) {
                headerBuilder.append(projectionInfoList.get(i).getEcl_eng_name());
                if (i < metaSelectedColumnInfoListSize - 1)
                    headerBuilder.append(',');
            }
        }

        return headerBuilder.toString();
    }
}