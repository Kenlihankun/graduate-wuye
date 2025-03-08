package com.cy.chat.deepseek.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.chat.deepseek.entity.ChatRequest;
import com.cy.chat.deepseek.entity.ChatResponse;
import com.cy.noticeManagement.sys_notice.entity.SysNotice;
import com.cy.noticeManagement.sys_notice.service.SysNoticeService;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OpenAIService {
    @Autowired
    private SysNoticeService sysNoticeService;

    // 设置超时时间
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(300, TimeUnit.SECONDS)  // 连接超时
            .readTimeout(300, TimeUnit.SECONDS)     // 读取超时
            .writeTimeout(300, TimeUnit.SECONDS)    // 写入超时
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getResponse(String userMessage, boolean isPropertyRelated) throws IOException {
        String otherMessage = "";
        QueryWrapper<SysNotice> queryWrapper = new QueryWrapper<>();

        // 添加查询条件（如果需要）
        // queryWrapper.eq("some_field", someValue);  // 示例条件

        // 查询结果
        List<SysNotice> notices = sysNoticeService.list(queryWrapper);

        // 提取 title 和 content 字段
        List<String> titlesAndContents = notices.stream()
                .map(notice -> notice.getTitle() + ": " + notice.getNoticeContent()) // 组合两个字段
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(titlesAndContents)) {
            otherMessage = titlesAndContents.toString();
        }

        String jsonBody = objectMapper.writeValueAsString(new ChatRequest(userMessage,isPropertyRelated,otherMessage));

        String baseUrl = "http://127.0.0.1:11434/v1";
        String apiKey = "lm-studio";
        Request request = new Request.Builder()
                .url(baseUrl + "/chat/completions")
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            ChatResponse chatResponse = objectMapper.readValue(response.body().string(), ChatResponse.class);
            String result = chatResponse.getChoices().get(0).getMessage().getContent();
             // 去掉 <think> 标签及其内容
            return result.replaceAll("(?s)<think>.*?</think>", "").trim();

        }
    }
}