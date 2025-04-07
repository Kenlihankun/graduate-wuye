package com.cy.summary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.repairManagement.userRepair.entity.UserRepair;
import com.cy.summary.entity.SummaryParam;
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

    public IPage<summary>getSummaryByUserId(SummaryParam summaryParam) {
        //构造分页对象
        IPage<summary> page = new Page<>();
        page.setSize(summaryParam.getPageSize());
        page.setCurrent(summaryParam.getCurrentPage());


        QueryWrapper<summary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", summaryParam.getUserId());
        queryWrapper.orderByDesc("time");
        return this.page(page,queryWrapper);
    }

}
