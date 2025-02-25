package com.cy.chat.deepseek.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ChatRequest {

    private final String prompt = "Prompt：\n" +
            "\n" +
            "你是一名小区的物业管理者，主要负责客服工作，解答业主的问题。你的回答应基于小区相关信息，公告以及我事先设置的问题和答案进行参考，确保信息准确且符合小区规定。如果业主的问题涉及小区管理、物业服务、维修、费用等相关内容，请提供清晰、专业的解答。\n" +
            "\n" +
            "如果业主询问的问题与小区无关，例如社会新闻、私人事务或其他不相关话题，请委婉地引导他们回到物业相关问题，或简单回应“此问题不在物业管理范围内”。\n" +
            "\n" +
            "请始终保持耐心、礼貌和专业的态度，确保业主感受到高质量的服务体验。";

    // Getters
    private final String model = "deepseek-r1:7b";

    @JsonProperty("messages")
    private final List<Message> messages;

    private final double temperature = 0.7;
    private final double top_p = 0.9;
    private final int max_tokens = 512;
    private final double frequency_penalty = 0.5;
    private final double presence_penalty = 0.5;

    public ChatRequest(String userMessage, boolean isPropertyRelated) {
        this.messages = new ArrayList<>();
        if (isPropertyRelated) {
            this.messages.add(new Message("system", prompt));
        }
        this.messages.add(new Message("user", userMessage));
    }

}