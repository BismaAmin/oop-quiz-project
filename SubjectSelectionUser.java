package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;

public class SubjectSelectionUser extends JPanel {

    public SubjectSelectionUser(JFrame frame) {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblSelect = new JLabel("Select Subject");
        lblSelect.setFont(new Font("Arial", Font.BOLD, 24));
        lblSelect.setForeground(new Color(2, 77, 134)); // golden yellow

        String[] subjects = {"Mathematics", "Science", "History"};
        JComboBox<String> subjectDropdown = new JComboBox<>(subjects);
        subjectDropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        subjectDropdown.setPreferredSize(new Dimension(200, 30));

        JButton btnStart = new JButton("Start Quiz");
        JButton btnBack = new JButton("Back");

        Font btnFont = new Font("Arial", Font.BOLD, 20);
        Color btnColor = new Color(2, 77, 134);

        JButton[] buttons = {btnStart, btnBack};
        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(160, 50));
        }

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblSelect, gbc);

        gbc.gridy++;
        add(subjectDropdown, gbc);

        gbc.gridy++;
        add(btnStart, gbc);

        gbc.gridy++;
        add(btnBack, gbc);

        // Navigation
        btnStart.addActionListener(e -> {
            String subject = (String) subjectDropdown.getSelectedItem();
            QuestionManager qm = new QuestionManager(subject);
            java.util.List<Question> questions = qm.getAllQuestions();
            if (questions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No questions found for " + subject, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new QuizScreen(frame, questions));
                frame.revalidate();
                frame.repaint();
            }
        });

        btnBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new WelcomeScreen(frame));
            frame.revalidate();
            frame.repaint();
        });
    }
}
