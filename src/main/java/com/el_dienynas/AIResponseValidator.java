package com.el_dienynas;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class AIResponseValidator {

    public static String validateAIAnswer(
            JsonNode result,
            JsonNode exams,
            JsonNode homework) {

        JsonNode answerNode = result.path("answer");

        if (answerNode.asText().isBlank()) {
            return "DI atsakymas yra tuščias.";
        }

        String questionType = result.path("questionType").asText();

        if (questionType.equals("general")) {

            JsonNode consideredTasks = result.path("consideredTasks");

            if (!consideredTasks.isEmpty()) {
                return "Bendro klausimo užduočių sąrašas turi būti tuščias.";
            }

            return "OK";
        }

        if (!questionType.equals("diary")) {
            return "Neteisingas klausimo tipas.";
        }

        JsonNode consideredTasks = result.path("consideredTasks");

        if (!consideredTasks.isArray()) {
            return "DI negražino užduočių.";
        }

        Map<String, JsonNode> expectedTasks = new HashMap<>();

        for (JsonNode exam : exams) {
            expectedTasks.put("exam:" + exam.path("id").asText(), exam);
        }

        for (JsonNode task : homework) {
            expectedTasks.put("homework:" + task.path("id").asText(), task);
        }

        for (JsonNode task : consideredTasks) {

            String type = task.path("type").asText();
            String id = task.path("id").asText();

            if (!type.equals("exam") && !type.equals("homework")) {
                return "Neteisingas darbo tipas.";
            }

            String key = type + ":" + id;

            if (!expectedTasks.containsKey(key)) {
                return "DI pateikė neegzistuojantį darbą: " + key;
            }

            JsonNode originalTask = expectedTasks.get(key);

            String expectedSubject = originalTask.path("subject").asText();

            String actualSubject = task.path("subject").asText();

            if (!expectedSubject.equals(actualSubject)) {
                return "Neteisingas darbo dalykas: " + key;
            }

            String expectedDate;

            if (type.equals("exam")) {
                expectedDate = originalTask.path("date").asText();
            } else {
                expectedDate = originalTask.path("dueDate").asText();
            }

            String actualDate = task.path("date").asText();

            if (!expectedDate.equals(actualDate)) {
                return "Neteisinga darbo data: " + key;
            }
        }

        String priorityResult = validateTaskPriorities(consideredTasks);

        if (!priorityResult.equals("OK")) {
            return priorityResult;
        }

        return "OK";
    }

    public static String validateTaskPriorities(JsonNode tasks) {

        LocalDate previousDate = null;
        int previousPriority = -1;

        for (JsonNode task : tasks) {

            String type = task.path("type").asText();
            String date = task.path("date").asText();

            LocalDate currentDate;

            try {
                currentDate = LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                return "Neteisingas darbo datos formatas.";
            }

            int currentPriority;

            if (type.equals("exam")) {
                currentPriority = 1;
            } else if (type.equals("homework")) {
                currentPriority = 2;
            } else {
                return "Nežinomas darbo tipas.";
            }

            if (previousDate != null) {

                if (currentDate.isBefore(previousDate)) {
                    return "Nesilaikoma darbų datų tvarkos.";
                }

                if (currentDate.equals(previousDate)
                        && currentPriority < previousPriority) {

                    return "Nesilaikoma darbų prioritetų tvarkos.";
                }
            }

            previousDate = currentDate;
            previousPriority = currentPriority;
        }

        return "OK";
    }
}