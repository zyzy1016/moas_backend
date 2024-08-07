package com.moas.backend.service.analysis.records;

import com.moas.backend.dataobject.AnalysisRecords;
import com.moas.backend.enums.AnalysisTypeEnum;

import java.util.Date;

public interface AnalysisRecordsService {

    Long init(AnalysisTypeEnum typeEnum);

    int createRecord(String recordName, String recordPath, String analysisType, String analysisStatus);

    int modifyStatus(Long recordId, String analysisStatus);

    int endRecord(Long recordId, Date endTime, String analysisStatus, String endReason);

    int delete(Long recordId);

    AnalysisRecords queryById(Long recordId);
}
