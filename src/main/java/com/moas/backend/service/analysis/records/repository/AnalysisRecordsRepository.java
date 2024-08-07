package com.moas.backend.service.analysis.records.repository;

import com.moas.backend.dataobject.AnalysisRecords;

import java.util.List;

public interface AnalysisRecordsRepository {

    int update(AnalysisRecords analysisRecords);

    int insert(AnalysisRecords analysisRecords);

    int delete(AnalysisRecords analysisRecords);

    AnalysisRecords queryByRecordId(Long id);

    List<AnalysisRecords> queryRecordsList();

}
