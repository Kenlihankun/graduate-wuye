package com.cy.summary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.summary.entity.summary;
import com.cy.summary.summaryMapper.mapper.SummaryMapper;
import com.cy.summary.service.SummaryService;
import com.cy.summary.utils.MonthlyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SummaryServiceImpl extends ServiceImpl<SummaryMapper, summary> implements SummaryService {

    @Autowired
    private MonthlyTask monthlyTask;

    @Override
    public void saveSummary(summary summary) {
        this.save(summary);
    }

    public void testExecuteMonthlyTask() {
        try {
            monthlyTask.executeMonthlyTask();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<summary> getSummaryByUserId(String userId) {
        QueryWrapper<summary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.baseMapper.selectList(queryWrapper);
    }

}
