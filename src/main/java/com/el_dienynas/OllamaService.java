package com.el_dienynas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class OllamaService {

    private static final String OLLAMA_URL = "http://localhost:11434/api/chat";

    private static final String MODEL = "qwen3:8b";

    private final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient client = HttpClient.newHttpClient();

    private final StudentService studentService;

    private final ConversationHistory conversationHistory;

    public OllamaService(StudentService studentService, ConversationHistory conversationHistory) {

        this.studentService = studentService;
        this.conversationHistory = conversationHistory;
    }

    public String sendMessage(String message) throws Exception {

        JsonNode currentUser = studentService.getCurrentUser();

        JsonNode exams = currentUser.path("exams");
        JsonNode homework = currentUser.path("homework");

        ObjectNode studentInfo = mapper.createObjectNode();

        studentInfo.set("exams", exams);
        studentInfo.set("homework", homework);

        String studentData = mapper.writeValueAsString(studentInfo);

        ObjectNode requestBody = mapper.createObjectNode();

        requestBody.put("model", MODEL);
        requestBody.put("stream", false);
        requestBody.put("format", "json");

        ArrayNode messages = requestBody.putArray("messages");

        ObjectNode systemMessage = mapper.createObjectNode();

        systemMessage.put("role", "system");
        systemMessage.put("content", PromptBuilder.createSystemPrompt(studentData));

        messages.add(systemMessage);

        for (JsonNode historyMessage : conversationHistory.getHistory()) {
            messages.add(historyMessage.deepCopy());
        }

        ObjectNode userMessage = mapper.createObjectNode();

        userMessage.put("role", "user");
        userMessage.put("content", message);

        messages.add(userMessage);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OLLAMA_URL))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(120))
                .POST(HttpRequest.BodyPublishers.ofString(
                        mapper.writeValueAsString(requestBody)
                ))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "DI paslauga nepasiekiama. HTTP kodas: " + response.statusCode()
            );
        }

        JsonNode jsonResponse = mapper.readTree(response.body());

        String content = jsonResponse.path("message").path("content").asText();

        if (content == null || content.isBlank()) {
            throw new RuntimeException("DI atsakymas negautas.");
        }

        JsonNode structuredAnswer;

        try {
            structuredAnswer = mapper.readTree(content);

        } catch (Exception e) {
            throw new RuntimeException(
                    "DI pateikė netinkamo formato JSON atsakymą."
            );
        }

        String validationResult = AIResponseValidator.validateAIAnswer(structuredAnswer, exams, homework);

        if (!validationResult.equals("OK")) {
            throw new RuntimeException(validationResult);
        }

        conversationHistory.addUserMessage(message);

        conversationHistory.addAssistantMessage(content);

        return structuredAnswer.path("answer").asText();
    }
}