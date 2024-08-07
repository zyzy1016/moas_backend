package com.moas.backend.service.analysis.handler;

import com.moas.backend.enums.AnalysisTypeEnum;

import java.util.Map;

public interface AnalysisExecutorInterface {

    Long init();

    void execute(String filePath, Map<Object,Object> params, String uid);

}
