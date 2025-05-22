package com.mycompany.quizapplicationn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreHistoryManager {

    private static final String HISTORY_FILE = "score_history.txt";

    public static void saveScore(String username, String subject, int correct, int wrong) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            writer.write("User: " + username + " | Subject: " + subject + " | Correct: " + correct + " | Wrong: " + wrong);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllScores() {
        List<String> scores = new ArrayList<>();
        File file = new File(HISTORY_FILE);

        if (!file.exists()) {
            return scores;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scores;
    }
}
