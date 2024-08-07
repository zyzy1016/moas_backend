package com.moas.backend.service.analysis.records.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.moas.backend.dataobject.AnalysisRecords;
import com.moas.backend.enums.AnalysisStatus;
import com.moas.backend.enums.AnalysisTypeEnum;
import com.moas.backend.service.analysis.records.AnalysisRecordsService;
import com.moas.backend.service.analysis.records.repository.AnalysisRecordsRepository;
import com.moas.backend.toolkit.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
@Slf4j
public class AnalysisRecordsServiceImpl implements AnalysisRecordsService {

    @Autowired
    private AnalysisRecordsRepository analysisRecordsRepository;

    private AnalysisRecords buildAnalysisRecords(String recordName, String recordPath, String analysisType, String analysisStatus) {
        AnalysisRecords analysisRecords = new AnalysisRecords();
        analysisRecords.setRecordName(recordName);
        analysisRecords.setRecordPath(recordPath);
        analysisRecords.setAnalysisType(analysisType);
        analysisRecords.setAnalysisStatus(analysisStatus);
        return analysisRecords;
    }

    @Override
    public Long init(AnalysisTypeEnum typeEnum) {
        AnalysisRecords analysisRecords = new AnalysisRecords();
        analysisRecords.setRecordId(DateUtil.current()+1);
        analysisRecords.setAnalysisStatus(AnalysisStatus.NOT_STARTED.getCode());
        analysisRecords.setAnalysisType(typeEnum.getCode());
        // 新建文件夹保存分析结果
        String recordName = StrUtil.format("{}_{}", AnalysisTypeEnum.GENOME.getDesc(), DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss"));
        Path directoryPath = Paths.get(Constants.CONTEXT_PATH, recordName);
        try {
            Files.createDirectories(directoryPath);
        } catch (IOException e) {
            log.error("Failed to create directory: {}", directoryPath, e);
        }
        analysisRecords.setRecordPath(directoryPath.toString());
        analysisRecords.setRecordName(recordName);
        analysisRecordsRepository.insert(analysisRecords);
        return analysisRecords.getRecordId();
    }

    @Override
    public int createRecord(String recordName, String recordPath, String analysisType, String analysisStatus) {
        AnalysisRecords analysisRecords = buildAnalysisRecords(recordName, recordPath, analysisType, analysisStatus);
        return analysisRecordsRepository.insert(analysisRecords);
    }

    @Override
    public int modifyStatus(Long recordId, String analysisStatus) {
        AnalysisRecords analysisRecords = analysisRecordsRepository.queryByRecordId(recordId);
        analysisRecords.setAnalysisStatus(analysisStatus);
        return analysisRecordsRepository.update(analysisRecords);
    }

    @Override
    public int endRecord(Long recordId, Date endTime, String analysisStatus, String endReason) {
        AnalysisRecords analysisRecords = analysisRecordsRepository.queryByRecordId(recordId);
        analysisRecords.setEndTime(endTime);
        analysisRecords.setAnalysisStatus(analysisStatus);
        analysisRecords.setEndReason(endReason);
        return analysisRecordsRepository.update(analysisRecords);
    }

    @Override
    public int delete(Long recordId) {
        AnalysisRecords analysisRecords = analysisRecordsRepository.queryByRecordId(recordId);
        return analysisRecordsRepository.delete(analysisRecords);
    }

    @Override
    public AnalysisRecords queryById(Long recordId) {
        return analysisRecordsRepository.queryByRecordId(recordId);
    }


}
