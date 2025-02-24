package com.cy.chat.deepseek.service.impl;

import com.cy.chat.deepseek.entity.ChatRequest;
import com.cy.chat.deepseek.entity.ChatResponse;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class OpenAIService {

    // 设置超时时间
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // 连接超时
            .readTimeout(30, TimeUnit.SECONDS)     // 读取超时
            .writeTimeout(30, TimeUnit.SECONDS)    // 写入超时
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getResponse(String userMessage) throws IOException {
        String jsonBody = objectMapper.writeValueAsString(new ChatRequest(userMessage));

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
            return result.replaceAll("<think>\\s*</think>", "").trim();
        }
    }
}