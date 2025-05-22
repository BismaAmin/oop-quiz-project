package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CRUDOperationsPanel extends JPanel {

    public CRUDOperationsPanel(JFrame frame, String subject) {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitle = new JLabel("Manage Questions - " + subject);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(2,77,134));

        JButton btnAdd = new JButton("Add Question");
        JButton btnDelete = new JButton("Delete Question");
        JButton btnEdit = new JButton("Edit Question"); // ✅ New Edit Button
        JButton btnView = new JButton("View Questions");
        JButton btnBack = new JButton("Back");

        JButton[] buttons = {btnAdd, btnDelete, btnEdit, btnView, btnBack};

        Dimension buttonSize = new Dimension(200, 40);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = new Color(2,77,134);

        for (JButton btn : buttons) {
            btn.setPreferredSize(buttonSize);
            btn.setFont(buttonFont);
            btn.setBackground(buttonColor);
            btn.setForeground(Color.WHITE);
        }

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitle, gbc);

        gbc.gridy++;
        add(btnAdd, gbc);

        gbc.gridy++;
        add(btnDelete, gbc);

        gbc.gridy++;
        add(btnEdit, gbc);  // ✅ Add Edit Button to layout

        gbc.gridy++;
        add(btnView, gbc);

        gbc.gridy++;
        add(btnBack, gbc);

        // === ADD Question ===
        btnAdd.addActionListener(e -> {
            String questionText = JOptionPane.showInputDialog(frame, "Enter Question Text:");
            if (questionText == null || questionText.trim().isEmpty())
                return;

            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                options[i] = JOptionPane.showInputDialog(frame, "Enter Option " + (i + 1) + ":");
                if (options[i] == null || options[i].trim().isEmpty()) 
                    return;
            }

            String correct = (String) JOptionPane.showInputDialog(frame, "Select Correct Option:", "Correct Option",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (correct != null) {
                Question q = new Question(questionText, List.of(options), correct);
                QuestionManager qm = new QuestionManager(subject);
                qm.addQuestion(q);
                JOptionPane.showMessageDialog(frame, "Question added successfully.");
            }
        });

        // === DELETE Question ===
        btnDelete.addActionListener(e -> {
            QuestionManager qm = new QuestionManager(subject);
            List<Question> qs = qm.getAllQuestions();
            if (qs.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No questions to delete.");
                return;
            }

            String[] qTexts = qs.stream().map(Question::getQuestionText).toArray(String[]::new);
            String toDelete = (String) JOptionPane.showInputDialog(frame, "Select question to delete:", "Delete Question",
                    JOptionPane.QUESTION_MESSAGE, null, qTexts, qTexts[0]);

            if (toDelete != null) {
                qs.removeIf(q -> q.getQuestionText().equals(toDelete));
                qm.saveQuestions();
                JOptionPane.showMessageDialog(frame, "Question deleted successfully.");
            }
        });

        // === ✅ EDIT Question ===
        btnEdit.addActionListener(e -> {
            QuestionManager qm = new QuestionManager(subject);
            List<Question> qs = qm.getAllQuestions();
            if (qs.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No questions to edit.");
                return;
            }

            String[] qTexts = qs.stream().map(Question::getQuestionText).toArray(String[]::new);
            String toEdit = (String) JOptionPane.showInputDialog(frame, "Select question to edit:", "Edit Question",
                    JOptionPane.QUESTION_MESSAGE, null, qTexts, qTexts[0]);

            if (toEdit != null) {
                for (Question q : qs) {
                    if (q.getQuestionText().equals(toEdit)) {
                        String newQuestionText = JOptionPane.showInputDialog(frame, "Edit Question Text:", q.getQuestionText());
                        if (newQuestionText == null || newQuestionText.trim().isEmpty()) return;

                        String[] newOptions = new String[4];
                        for (int i = 0; i < 4; i++) {
                            newOptions[i] = JOptionPane.showInputDialog(frame, "Edit Option " + (i + 1) + ":", q.getOptions().get(i));
                            if (newOptions[i] == null || newOptions[i].trim().isEmpty()) return;
                        }

                        String newCorrect = (String) JOptionPane.showInputDialog(frame, "Select Correct Option:", "Correct Option",
                                JOptionPane.QUESTION_MESSAGE, null, newOptions, q.getCorrectOption());

                        if (newCorrect != null) {
                            q.setQuestionText(newQuestionText);
                            q.setOptions(List.of(newOptions));
                            q.setCorrectOption(newCorrect);
                            qm.saveQuestions();
                            JOptionPane.showMessageDialog(frame, "Question updated successfully.");
                        }
                        break;
                    }
                }
            }
        });

        // === VIEW Questions ===
        btnView.addActionListener(e -> {
            QuestionManager qm = new QuestionManager(subject);
            List<Question> qs = qm.getAllQuestions();
            if (qs.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No questions available.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Question q : qs) {
                    sb.append("Q: ").append(q.getQuestionText()).append("\n");
                    List<String> opts = q.getOptions();
                    for (int i = 0; i < opts.size(); i++) {
                        sb.append((char) ('A' + i)).append(". ").append(opts.get(i)).append("\n");
                    }
                    sb.append("Correct: ").append(q.getCorrectOption()).append("\n\n");
                }
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(frame, scrollPane, "All Questions", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // === BACK ===
        btnBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new WelcomeAdminScreen(frame));
            frame.revalidate();
            frame.repaint();
        });
    }
}
