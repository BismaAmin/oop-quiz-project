package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuizScreen extends JPanel {
    private JFrame frame;
    private List<Question> questions;
    private int currentIndex = 0;
    private int correct = 0;
    private int wrong = 0;
    private Timer timer;
    private JLabel lblTimer;
    private int timeLeft = 10;
    private JLabel lblQuestion;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton btnNext;

    public QuizScreen(JFrame frame, List<Question> questions) {
        this.frame = frame;
        this.questions = questions;

        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Question and Timer Panel
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBackground(Color.WHITE);

        lblQuestion = new JLabel();
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 20));
        lblQuestion.setForeground(new Color(2, 77, 138));
        questionPanel.add(lblQuestion, BorderLayout.WEST);

        lblTimer = new JLabel("Time: 10");
        lblTimer.setFont(new Font("Arial", Font.BOLD, 16));
        lblTimer.setForeground(Color.RED);
        questionPanel.add(lblTimer, BorderLayout.EAST);

        gbc.gridy = 0;
        add(questionPanel, gbc);

        // Options Panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setPreferredSize(new Dimension(400, 150));

        options = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBackground(Color.WHITE);
            options[i].setForeground(new Color(2, 77, 138));
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }

        gbc.gridy = 1;
        add(optionsPanel, gbc);

        // Next Button
        btnNext = new JButton("Next");
        btnNext.setFont(new Font("Arial", Font.BOLD, 18));
        btnNext.setBackground(new Color(2, 77, 138));
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusPainted(false);
        btnNext.setPreferredSize(new Dimension(150, 40));
        btnNext.addActionListener(e -> evaluateAnswer());

        gbc.gridy = 2;
        add(btnNext, gbc);

        loadQuestion();
        startTimer();
    }

    private void loadQuestion() {
        if (currentIndex >= questions.size()) {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new ScoreScreen(frame, correct, wrong));
            frame.revalidate();
            frame.repaint();
            return;
        }

        Question q = questions.get(currentIndex);
        lblQuestion.setText((currentIndex + 1) + ". " + q.getQuestionText());
        List<String> opts = q.getOptions();
        for (int i = 0; i < 4; i++) {
            options[i].setText(opts.get(i));
        }
        group.clearSelection();
        timeLeft = 10;
        lblTimer.setText("Time: " + timeLeft);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                lblTimer.setText("Time: " + timeLeft);
                if (timeLeft == 0) {
                    evaluateAnswer();
                }
            }
        });
        timer.start();
    }

    private void evaluateAnswer() {
        timer.stop();
        String selected = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selected = option.getText();
                break;
            }
        }
        String correctOption = questions.get(currentIndex).getCorrectOption();
        if (correctOption.equals(selected)) {
            correct++;
        } else {
            wrong++;
        }
        currentIndex++;
        loadQuestion();
        timer.restart();
    }
}
