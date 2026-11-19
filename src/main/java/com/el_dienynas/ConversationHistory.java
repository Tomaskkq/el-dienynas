package com.el_dienynas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ConversationHistory {

    private static final int MAX_HISTORY_MESSAGES = 20;

    private final ObjectMapper mapper = new ObjectMapper();

    private final ArrayNode conversationHistory =
            mapper.createArrayNode();

    public void addUserMessage(String message) {

        ObjectNode userMessage = mapper.createObjectNode();

        userMessage.put("role", "user");
        userMessage.put("content", message);

        conversationHistory.add(userMessage);

        limitConversationHistory();
    }

    public void addAssistantMessage(String content) {

        ObjectNode assistantMessage = mapper.createObjectNode();

        assistantMessage.put("role", "assistant");
        assistantMessage.put("content", content);

        conversationHistory.add(assistantMessage);

        limitConversationHistory();
    }

    public ArrayNode getHistory() {

        return conversationHistory;
    }

    private void limitConversationHistory() {

        while (conversationHistory.size() > MAX_HISTORY_MESSAGES) {

            conversationHistory.remove(0);
        }
    }
}