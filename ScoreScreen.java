package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ScoreScreen extends JPanel {

    public ScoreScreen(JFrame frame, int correct, int wrong) {
        setLayout(new BorderLayout());  // Main layout
        setBackground(Color.WHITE);

        // MAIN PANEL: split into two sides
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // LEFT PANEL: image
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        URL imageUrl = getClass().getClassLoader().getResource("ICONS/16.png");
        if (imageUrl != null) {
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            Image scaledImage = imageIcon.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            leftPanel.add(imageLabel, BorderLayout.CENTER);
        } else {
            leftPanel.add(new JLabel("Image not found"), BorderLayout.CENTER);
        }

        // RIGHT PANEL: score display
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblResult = new JLabel("Quiz Completed!");
        lblResult.setFont(new Font("Arial", Font.BOLD, 28));
        lblResult.setForeground(new Color(2, 77, 134));

        JLabel lblCorrect = new JLabel("Correct Answers: " + correct);
        lblCorrect.setFont(new Font("Arial", Font.PLAIN, 20));
        lblCorrect.setForeground(new Color(2, 77, 134));

        JLabel lblWrong = new JLabel("Wrong Answers: " + wrong);
        lblWrong.setFont(new Font("Arial", Font.PLAIN, 20));
        lblWrong.setForeground(new Color(2, 77, 134));

        JButton btnBack = new JButton("Back to Login");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(2, 77, 134));
        btnBack.setForeground(Color.WHITE);

        JButton btnHistory = new JButton("View Score History");
        btnHistory.setFont(new Font("Arial", Font.BOLD, 16));
        btnHistory.setBackground(new Color(2, 77, 134));
        btnHistory.setForeground(Color.WHITE);

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(lblResult, gbc);

        gbc.gridy++;
        rightPanel.add(lblCorrect, gbc);

        gbc.gridy++;
        rightPanel.add(lblWrong, gbc);

        gbc.gridy++;
        rightPanel.add(btnBack, gbc);

        gbc.gridy++;
        rightPanel.add(btnHistory, gbc);

        // Save the score
        ScoreHistoryManager.saveScore("Guest", "General", correct, wrong);

        // Button Actions
        btnBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new LoginScreen(frame));
            frame.revalidate();
            frame.repaint();
        });

        btnHistory.addActionListener(e -> {
            ScoreHistoryViewer.showScoreHistory(frame);
        });

        // Add left and right panels to main panel
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        // Add main panel to this JPanel
        add(mainPanel, BorderLayout.CENTER);
    }
}
