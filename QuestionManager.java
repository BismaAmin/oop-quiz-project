package com.mycompany.quizapplicationn;

import java.io.*;
import java.util.*;

public class QuestionManager {
    private String subject;
    private File file;
    private List<Question> questions;

    public QuestionManager(String subject) {
        this.subject = subject;
        file = new File(subject + ".txt");
        questions = loadQuestions();
    }

    private List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Q:")) {
                    String questionText = line.substring(2).trim();
                    List<String> options = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        options.add(reader.readLine());
                    }
                    String correct = reader.readLine();
                    list.add(new Question(questionText, options, correct));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addQuestion(Question q) {
        questions.add(q);
        saveQuestions();
    }

    public void deleteQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
            saveQuestions();
        }
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    void saveQuestions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Question q : questions) {
                writer.write("Q:" + q.getQuestionText());
                writer.newLine();
                for (String opt : q.getOptions()) {
                    writer.write(opt);
                    writer.newLine();
                }
                writer.write(q.getCorrectOption());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
