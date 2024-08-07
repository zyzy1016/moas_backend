package com.moas.backend.controller.analysisRecords;

import com.moas.backend.controller.resp.ResponseModel;
import com.moas.backend.controller.analysisRecords.req.BaseRecordReq;
import com.moas.backend.controller.analysisRecords.req.ModifyStatusReq;
import com.moas.backend.service.analysis.records.AnalysisRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/analysisRecords")
public class AnalysisRecordsController {

    @Autowired
    private AnalysisRecordsService analysisRecordsService;

    @PostMapping("/createRecord")
    public int createRecord(String recordName, String recordPath, String analysisType, String analysisStatus) {
        return analysisRecordsService.createRecord(recordName, recordPath, analysisType, analysisStatus);
    }

    @DeleteMapping("/deleteRecord")
    public ResponseModel<Boolean> deleteRecord(@RequestBody BaseRecordReq req) {
       analysisRecordsService.delete(req.getRecordId());
       return ResponseModel.success(true);
    }

    @PostMapping("/modifyStatus")
    public ResponseModel<Boolean> modifyStatus(@RequestBody ModifyStatusReq req) {
        analysisRecordsService.modifyStatus(req.getRecordId(), req.getAnalysisStatus());
        return ResponseModel.success(true);
    }

    @PostMapping("/endRecord")
    public ResponseModel<Boolean> endRecord(Long recordId, String analysisStatus, String endReason) {
        analysisRecordsService.endRecord(recordId, new Date(), analysisStatus, endReason);
        return ResponseModel.success(true);
    }


}
