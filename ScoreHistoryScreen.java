
package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreHistoryScreen extends JPanel {

    private JFrame frame;

    public ScoreHistoryScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Score History");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(2, 77, 134));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setForeground(new Color(2, 77, 134));

        List<String> scores = ScoreHistoryManager.getAllScores();
        if (scores.isEmpty()) {
            textArea.setText("No score history found.");
        } else {
            for (String line : scores) {
                textArea.append(line + "\n");
            }
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(2, 77, 134));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new WelcomeAdminScreen(frame));
            frame.revalidate();
            frame.repaint();
        });

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.WHITE);
        southPanel.add(backButton);
        add(southPanel, BorderLayout.SOUTH);
    }
}
