package com.cy.chat.deepseek.service.impl;

import com.cy.chat.deepseek.entity.ChatRequest;
import com.cy.chat.deepseek.entity.ChatResponse;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenAIService{
    private final String baseUrl = "http://127.0.0.1:11434/v1";
    private final String apiKey = "lm-studio";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getResponse(String userMessage) throws IOException {
        String jsonBody = objectMapper.writeValueAsString(new ChatRequest(userMessage));

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
            return chatResponse.getChoices().get(0).getMessage().getContent();
        }
    }
}