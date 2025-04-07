package com.cy.summary.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.repairManagement.userRepair.entity.UserRepair;
import com.cy.summary.entity.SummaryParam;
import com.cy.summary.entity.summary;
import com.cy.summary.service.impl.SummaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 总结模块
 */
@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    @Autowired
    private SummaryServiceImpl summaryService;

    /**
     *
     * @param summaryParam
     * @return
     */
    @GetMapping("/get")
    public CommonResult<IPage<summary>> getList(@Valid SummaryParam summaryParam) throws IOException {

        return CommonResult.success("查询成功",summaryService.getSummaryByUserId(summaryParam));
    }

    /**
     *
     * @return
     */
    @GetMapping("/backdoor/get")
    public void testTask() {

        summaryService.testExecuteMonthlyTask();
    }
}
