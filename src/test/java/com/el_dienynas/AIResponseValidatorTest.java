package com.el_dienynas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AIResponseValidatorTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldAcceptCorrectAnswer() throws Exception {

        JsonNode result = mapper.readTree("""
            {
                "answer": "Matematikos kontrolinis darbas vyks 2026-11-17.",
                "questionType": "diary",
                "consideredTasks": [
                    {
                        "id": "1",
                        "type": "exam",
                        "subject": "Matematika",
                        "date": "2026-11-17"
                    }
                ]
            }
            """);

        JsonNode exams = mapper.readTree("""
            [
                {
                    "id": "1",
                    "subject": "Matematika",
                    "date": "2026-11-17"
                }
            ]
            """);

        JsonNode homework = mapper.readTree("[]");

        String actual = AIResponseValidator.validateAIAnswer(result, exams, homework);

        assertEquals("OK", actual);
    }

    @Test
    void shouldRejectIncorrectTaskDate() throws Exception {

        JsonNode result = mapper.readTree("""
            {
                "answer": "Matematikos kontrolinis darbas vyks 2026-11-28.",
                "questionType": "diary",
                "consideredTasks": [
                    {
                        "id": "1",
                        "type": "exam",
                        "subject": "Matematika",
                        "date": "2026-11-28"
                    }
                ]
            }
            """);

        JsonNode exams = mapper.readTree("""
            [
                {
                    "id": "1",
                    "subject": "Matematika",
                    "date": "2026-11-17"
                }
            ]
            """);

        JsonNode homework = mapper.readTree("[]");

        String actual = AIResponseValidator.validateAIAnswer(result, exams, homework);

        assertEquals("Neteisinga darbo data: exam:1", actual);
    }

    @Test
    void shouldRejectNonExistingTask() throws Exception {

        JsonNode result = mapper.readTree("""
            {
                "answer": "Fizikos kontrolinis darbas vyks 2026-11-19.",
                "questionType": "diary",
                "consideredTasks": [
                    {
                        "id": "99",
                        "type": "exam",
                        "subject": "Fizika",
                        "date": "2026-11-19"
                    }
                ]
            }
            """);

        JsonNode exams = mapper.readTree("[]");
        JsonNode homework = mapper.readTree("[]");

        String actual = AIResponseValidator.validateAIAnswer(result, exams, homework);

        assertEquals("DI pateikė neegzistuojantį darbą: exam:99", actual);
    }

    @Test
    void shouldIsolateDifferentStudentsDiaryData() throws Exception {

        JsonNode studentAExams = mapper.readTree("""
            [
                {
                    "id": "1",
                    "subject": "Matematika",
                    "date": "2026-11-17"
                }
            ]
            """);

        JsonNode studentBExams = mapper.readTree("[]");
        JsonNode homework = mapper.readTree("[]");

        JsonNode result = mapper.readTree("""
            {
                "answer": "Matematikos kontrolinis darbas vyks 2026-11-17.",
                "questionType": "diary",
                "consideredTasks": [
                    {
                        "id": "1",
                        "type": "exam",
                        "subject": "Matematika",
                        "date": "2026-11-17"
                    }
                ]
            }
            """);

        String resultA = AIResponseValidator.validateAIAnswer(result, studentAExams, homework);

        String resultB = AIResponseValidator.validateAIAnswer(result, studentBExams, homework);

        assertEquals("OK", resultA);

        assertEquals("DI pateikė neegzistuojantį darbą: exam:1", resultB);
    }
}
