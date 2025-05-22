package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;

public class WelcomeAdminScreen extends JPanel {

    public WelcomeAdminScreen(JFrame frame) {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitle = new JLabel("Admin Panel");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(new Color(2, 77, 134));

        String[] subjects = {"Mathematics", "Science", "History"};
        JComboBox<String> subjectDropdown = new JComboBox<>(subjects);
        subjectDropdown.setPreferredSize(new Dimension(200, 40));
        subjectDropdown.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton btnManage = new JButton("Manage Questions");
        JButton btnBack = new JButton("Logout");

        // Style buttons
        Dimension buttonSize = new Dimension(200, 40);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        btnManage.setPreferredSize(buttonSize);
        btnManage.setFont(buttonFont);
        btnManage.setBackground(new Color(2, 77, 134));
        btnManage.setForeground(Color.WHITE);

        btnBack.setPreferredSize(buttonSize);
        btnBack.setFont(buttonFont);
        btnBack.setBackground(new Color(2, 77, 134));
        btnBack.setForeground(Color.WHITE);

        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitle, gbc);

        gbc.gridy++;
        add(subjectDropdown, gbc);

        gbc.gridy++;
        add(btnManage, gbc);

        gbc.gridy++;
        add(btnBack, gbc);

        // Action listeners
        btnManage.addActionListener(e -> {
            String subject = (String) subjectDropdown.getSelectedItem();
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new CRUDOperationsPanel(frame, subject));
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
