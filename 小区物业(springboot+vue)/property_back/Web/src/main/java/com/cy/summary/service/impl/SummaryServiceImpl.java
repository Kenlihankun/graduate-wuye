package com.cy.summary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.summary.entity.summary;
import com.cy.summary.summaryMapper.mapper.SummaryMapper;
import com.cy.summary.service.SummaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryServiceImpl extends ServiceImpl<SummaryMapper, summary> implements SummaryService {

    @Override
    public void saveSummary(summary summary) {
        this.save(summary);
    }

    public List<summary> getSummaryByUserId(String userId) {
        QueryWrapper<summary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.baseMapper.selectList(queryWrapper);
    }

}
