package com.cy.chat.deepseek.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.CommonResult;
import com.cy.chat.deepseek.service.impl.OpenAIService;
import com.cy.complaintManagement.userComplaint.entity.UserComplaint;
import com.cy.complaintManagement.userComplaint.entity.UserComplaintParam;
import com.cy.complaintManagement.userComplaint.service.UserComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 23:01:30
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private OpenAIService openAIService;


    /**
     *
     * @param param
     * @return
     */
    @GetMapping("/get")
    public CommonResult<String> getList( String param) throws IOException {

        return CommonResult.success(openAIService.getResponse(param));
    }
}
