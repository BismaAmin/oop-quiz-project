package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesScreen extends JPanel {

    private JFrame frame;

    public RulesScreen(JFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title label with custom color
        JLabel title = new JLabel("Quiz Rules");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(2, 77, 134)); // Your specified color
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Panel to center rules
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Rule list
        String[] rules = {
            "1. You must select an answer within 10 seconds.",
            "2. Only one option can be selected per question.",
            "3. Each correct answer gives you 1 point.",
            "4. No points are deducted for wrong answers.",
            "5. You cannot go back to the previous question.",
            "6. Make sure to read all options carefully.",
            "7. Your final score will be shown at the end.",
            "8. Don’t close the window during the quiz.",
            "9. Quiz starts immediately after clicking 'Start'.",
            "10. Your score will be saved in history after the quiz.",
            "11. Ensure a stable internet/power connection (if required)."
        };

        for (int i = 0; i < rules.length; i++) {
            JLabel ruleLabel = new JLabel(rules[i]);
            ruleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            ruleLabel.setForeground(new Color(2, 77, 134)); // Your specified color
            gbc.gridy = i;
            centerPanel.add(ruleLabel, gbc);
        }

        add(centerPanel, BorderLayout.CENTER);

        // Start button with color
        JButton btnStart = new JButton("Start Quiz");
        btnStart.setBackground(new Color(2, 77, 134)); // Button background
        btnStart.setForeground(Color.WHITE); // Button text
        btnStart.setFont(new Font("Arial", Font.BOLD, 16));
        btnStart.setFocusPainted(false);
        btnStart.setPreferredSize(new Dimension(150, 40));
        btnStart.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate to subject selection or quiz
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SubjectSelectionUser(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.WHITE);
        southPanel.add(btnStart);
        add(southPanel, BorderLayout.SOUTH);
    }
}
