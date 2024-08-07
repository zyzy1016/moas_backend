package com.moas.backend.service.analysis.handler.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.moas.backend.dataobject.AnalysisRecords;
import com.moas.backend.dataobject.model.AnalysisContext;
import com.moas.backend.enums.AnalysisStatus;
import com.moas.backend.enums.AnalysisTypeEnum;
import com.moas.backend.service.analysis.handler.AnalysisExecutorInterface;
import com.moas.backend.service.analysis.records.AnalysisRecordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public class GenomeExecutor implements AnalysisExecutorInterface {

    @Autowired
    private AnalysisRecordsService analysisRecordsService;

    @Override
    public Long init() {
        return analysisRecordsService.init(AnalysisTypeEnum.GENOME);
    }

    @Override
    public void execute(String filePath, Map<Object, Object> params, String uid) {
        // 1. 初始化分析记录
        Long initId = init();
        AnalysisRecords analysisRecords = analysisRecordsService.queryById(initId);
        if (analysisRecords == null) {
            log.error("Failed to get analysis records by id: {}", initId);
            return;
        }
        //2. 设置当前分析记录
        AnalysisContext.setCurrentAnalysis(analysisRecords);
        //3. 执行分析
        log.info("Start to execute genome analysis, file path: {}, params: {}, uid: {}", filePath, params, uid);
        //3.1 开启监视器

    }
}
