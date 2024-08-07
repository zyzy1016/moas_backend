package com.moas.backend.dataobject.model;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.moas.backend.dataobject.AnalysisRecords;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 分析上下文，用于存储分析上下文的数据
 */
public class AnalysisContext {

    public static final String CURRENT_ANALYSIS_KEY = "current_analysis";

    private static final TransmittableThreadLocal<Map<Object, Object>> THREAD_LOCAL = new TransmittableThreadLocal() {

        @Override
        public Object copy(Object parentValue) {
            return parentValue != null ? (Map) ((HashMap<?, ?>) parentValue).clone() : null;
        }

        @Override
        protected Object childValue(Object parentValue) {
            return copy(parentValue);
        }

        @Override
        protected Object initialValue() { return new HashMap<>(); }
    };

    public static AnalysisRecords getCurrentAnalysis() {
        return (AnalysisRecords) THREAD_LOCAL.get().get(CURRENT_ANALYSIS_KEY);
    }

    public static void setCurrentAnalysis(AnalysisRecords analysisRecords) {
        if(Objects.nonNull(analysisRecords)) {
            Map<Object, Object> map = THREAD_LOCAL.get();
            if(Objects.isNull(map)) {
                map = new HashMap<>();
                map.put(CURRENT_ANALYSIS_KEY, analysisRecords);
                THREAD_LOCAL.set(map);
                return;
            }
            map.put(CURRENT_ANALYSIS_KEY, analysisRecords);
        }
    }

    public static void clearCurrentAnalysis() {
        Map<Object, Object> map = THREAD_LOCAL.get();
        if(Objects.nonNull(map)) {
            map.remove(CURRENT_ANALYSIS_KEY);
        }
    }


}
