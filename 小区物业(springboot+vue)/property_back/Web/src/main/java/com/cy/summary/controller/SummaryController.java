package com.cy.summary.controller;

import com.cy.CommonResult;
import com.cy.summary.entity.summary;
import com.cy.summary.service.impl.SummaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param userId
     * @return
     */
    @GetMapping("/get")
    public CommonResult<List<summary>> getList(String userId) throws IOException {

        return CommonResult.success("查询成功",summaryService.getSummaryByUserId(userId));
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
