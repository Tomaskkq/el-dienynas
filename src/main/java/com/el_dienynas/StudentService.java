package com.el_dienynas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentService {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final int CURRENT_USER_ID = 1;

    private static final String STUDENTS_JSON = """
    {
      "students": [
        {
          "id": 1,
          "firstName": "Jonas",
          "lastName": "Petraitis",
          "class": "10A",
          "homework": [
            {
              "id": 1,
              "subject": "Matematika",
              "description": "Uzdaviniai 15-25",
              "dueDate": "2026-11-18"
            }
          ],
          "exams": [
            {
              "id": 1,
              "subject": "Matematika",
              "topic": "Kvadratines lygtys",
              "date": "2026-11-17"
            },
            {
              "id": 2,
              "subject": "Istorija",
              "topic": "Lietuvos istorija XX a.",
              "date": "2026-11-25"
            }
          ]
        },
        {
          "id": 2,
          "firstName": "Austeja",
          "lastName": "Kazlauskaite",
          "class": "10A",
          "homework": [
            {
              "id": 1,
              "subject": "Fizika",
              "description": "Elektros sroves formules",
              "dueDate": "2026-11-19"
            }
          ],
          "exams": [
            {
              "id": 1,
              "subject": "Chemija",
              "topic": "Cheminiai elementai",
              "date": "2026-11-24"
            }
          ]
        }
      ]
    }
    """;

    public JsonNode getCurrentUser() throws Exception {

        JsonNode root = mapper.readTree(STUDENTS_JSON);

        for (JsonNode student : root.path("students")) {
            if (student.path("id").asInt() == CURRENT_USER_ID) {
                return student;
            }
        }

        throw new RuntimeException("Mokinys nerastas!");
    }
}