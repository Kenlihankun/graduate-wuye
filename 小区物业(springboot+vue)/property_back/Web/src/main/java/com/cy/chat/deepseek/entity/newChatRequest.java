package com.cy.chat.deepseek.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class newChatRequest {

    private final String prompt1 = "Prompt：The user is a tenant in the community. Please provide suggestions and summary based on the current month's water fee, electricity fee, and parking fee." +
            "user is the user name:%s park fee:%s,water fee:%s,power fee:%s";

    private final String prompt2 = "Prompt：The user is the manager of the community. Please provide management suggestions and summary based on the current month's repair status and complaint situations." +
            "name:%s,complaint:%s,repair:%s";

    // Getters
    private final String model = "deepseek-r1:7b";

    @JsonProperty("messages")
    private final List<Message> messages;

    private final double temperature = 0.7;
    private final double top_p = 0.9;
    private final int max_tokens = 512;
    private final double frequency_penalty = 0.5;
    private final double presence_penalty = 0.5;

    public newChatRequest(String name,String otherMessage1, String otherMessage2, String otherMessage3) {
        this.messages = new ArrayList<>();
        if (StringUtils.isNotEmpty(otherMessage3)) {
            this.messages.add(new Message("system", String.format(prompt1, name,otherMessage1, otherMessage2, otherMessage3)));
        } else {
            this.messages.add(new Message("system", String.format(prompt2, name,otherMessage1, otherMessage2)));
        }

    }

}