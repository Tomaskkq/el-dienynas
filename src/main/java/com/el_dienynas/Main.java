package com.el_dienynas;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        ConversationHistory conversationHistory = new ConversationHistory();

        OllamaService ollamaService = new OllamaService(studentService, conversationHistory);

        System.out.println("DI mokymosi asistentas");
        System.out.println("----------------------");

        try {
            System.out.println("Jungiamasi prie Ollama...");

            String response = ollamaService.sendMessage("Sveiki!");

            System.out.println("Prisijungimas sėkmingas!");
            System.out.println("DI atsakymas: " + response);

            while (true) {

                System.out.print("\nTu: ");
                String question = scanner.nextLine();

                if (question.equalsIgnoreCase("stop")) {
                    break;
                }

                try {
                    String answer = ollamaService.sendMessage(question);

                    System.out.println("\nDI: " + answer);

                } catch (Exception e) {

                    System.out.println("\nKLAIDA: Nepavyko gauti tinkamo DI atsakymo.");
                    System.out.println("Informacija: " + e.getMessage());

                    System.out.println("Galite bandyti užklausą pakartoti.");
                }
            }

        } catch (Exception e) {

            System.out.println("Klaida jungiantis prie Ollama!");
            System.out.println("Informacija: " + e.getMessage());
        }

        scanner.close();

        System.out.println("Programa baigta.");
    }
}