package com.moas.backend.enums;

import lombok.Getter;

@Getter
public enum AnalysisStatus {
    //未开始，分析中，分析完成，分析失败
    NOT_STARTED("notStarted", "未开始"),
    ANALYZING("analyzing", "分析中"),
    ANALYSIS_COMPLETED("analysisCompleted", "分析完成"),
    ANALYSIS_FAILED("analysisFailed", "分析失败");

    private final String code;

    private final String desc;

    AnalysisStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AnalysisStatus getByCode(String code) {
        for (AnalysisStatus value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
