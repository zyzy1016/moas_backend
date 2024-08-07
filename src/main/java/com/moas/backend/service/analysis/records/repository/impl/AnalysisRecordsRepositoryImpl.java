package com.moas.backend.service.analysis.records.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moas.backend.dataobject.AnalysisRecords;
import com.moas.backend.mapper.AnalysisRecordsMapper;
import com.moas.backend.service.analysis.records.repository.AnalysisRecordsRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class AnalysisRecordsRepositoryImpl implements AnalysisRecordsRepository {

    @Resource
    private AnalysisRecordsMapper analysisRecordsMapper;

    @Override
    public int update(AnalysisRecords analysisRecords) {
        return analysisRecordsMapper.updateById(analysisRecords);
    }

    @Override
    public int insert(AnalysisRecords analysisRecords) {
        return analysisRecordsMapper.insert(analysisRecords);
    }

    @Override
    public int delete(AnalysisRecords analysisRecords) {
        return analysisRecordsMapper.deleteById(analysisRecords.getRecordId());
    }

    @Override
    public AnalysisRecords queryByRecordId(Long id) {
        return analysisRecordsMapper.selectById(id);
    }

    @Override
    public List<AnalysisRecords> queryRecordsList() {
        return analysisRecordsMapper.selectList(new QueryWrapper<>());
    }

}
