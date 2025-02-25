package com.cy.chat.deepseek.controller;

import com.cy.CommonResult;
import com.cy.chat.deepseek.service.impl.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult<String> getList( String param, boolean isPropertyRelated) throws IOException {

        return CommonResult.success(openAIService.getResponse(param, isPropertyRelated));
    }
}
