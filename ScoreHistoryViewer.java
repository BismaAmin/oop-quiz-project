package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreHistoryViewer {

    public static void showScoreHistory(JFrame frame) {
        List<String> scores = ScoreHistoryManager.getAllScores();

        if (scores.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No score history available.", "Score History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String score : scores) {
            sb.append(score).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, "Score History", JOptionPane.INFORMATION_MESSAGE);
    }
}
