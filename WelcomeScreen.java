package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JPanel {

    public WelcomeScreen(JFrame frame) {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Removed the topPanel with banner image completely

        // CENTER PANEL
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.gridwidth = 3;

        try {
            ImageIcon icon = new ImageIcon("src/main/resources/ICONS/4.jpg");
            Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(image));
            gbc.gridx = 0;
            gbc.gridy = 0;
            centerPanel.add(imgLabel, gbc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblWelcome = new JLabel("Welcome to MindVoyage!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 36));
        lblWelcome.setForeground(new Color(2,77,134));
        gbc.gridy = 1;
        centerPanel.add(lblWelcome, gbc);

        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Color buttonColor = new Color(2,77,134);

        JButton btnStartQuiz = new JButton("Start Quiz");
        JButton btnRules = new JButton("View Rules");
        JButton btnBack = new JButton("Back");
        

        JButton[] buttons = {btnStartQuiz, btnRules, btnBack};
        for (JButton btn : buttons) {
            btn.setFont(buttonFont);
            btn.setBackground(buttonColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(160, 50));
        }

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        centerPanel.add(btnStartQuiz, gbc);

        gbc.gridx = 1;
        centerPanel.add(btnRules, gbc);

        gbc.gridx = 2;
        centerPanel.add(btnBack, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Navigation
        btnStartQuiz.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new SubjectSelectionUser(frame));
            frame.revalidate();
            frame.repaint();
        });

        btnRules.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new RulesScreen(frame));
            frame.revalidate();
            frame.repaint();
        });

        btnBack.addActionListener(e -> {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(new LoginScreen(frame));
    frame.revalidate();
    frame.repaint();
});
    }
}
