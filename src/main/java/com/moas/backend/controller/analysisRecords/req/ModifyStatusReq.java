package com.moas.backend.controller.analysisRecords.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyStatusReq extends BaseRecordReq{

    private String analysisStatus;
}
