package com.moas.backend.enums;

import lombok.Getter;

@Getter
public enum AnalysisTypeEnum {
    //基因组，转录组，蛋白质组、代谢组以及单细胞多组学等
    GENOME("", "基因组"),
    TRANSCRIPTOME("transcriptome", "转录组"),
    PROTEOME("proteome", "蛋白质组"),
    METABOLOME("metabolome", "代谢组"),
    SINGLE_CELL("singleCell", "单细胞多组学");

    private final String code;

    private final String desc;

    AnalysisTypeEnum(String  code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AnalysisTypeEnum getByCode(String code) {
        for (AnalysisTypeEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static AnalysisTypeEnum getByDesc(String desc) {
        for (AnalysisTypeEnum value : values()) {
            if (value.desc.equals(desc)) {
                return value;
            }
        }
        return null;
    }
}
