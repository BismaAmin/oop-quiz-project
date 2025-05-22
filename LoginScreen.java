package com.mycompany.quizapplicationn;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginScreen extends JPanel {

    private JFrame frame;

    public LoginScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create main panel (left: image, right: login)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        // Left panel for image
// Left panel for image
JPanel leftPanel = new JPanel();
leftPanel.setPreferredSize(new Dimension(400, 500));
leftPanel.setBackground(Color.WHITE);
leftPanel.setLayout(new BorderLayout()); // For stretching to fill the panel

// Load and scale image
URL imageUrl = getClass().getClassLoader().getResource("ICONS/4.jpg");
if (imageUrl != null) {
    ImageIcon imageIcon = new ImageIcon(imageUrl);
    Image scaledImage = imageIcon.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH); // Stretch to fit exactly
    JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setVerticalAlignment(SwingConstants.CENTER);

    leftPanel.add(imageLabel, BorderLayout.CENTER); // Fills the panel
} else {
    System.err.println("Image not found at ICONS/14.png");
}




        // Right panel for login fields
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitle = new JLabel("MindVoyage - Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(new Color(1,71,126)); // Golden yellow

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameField.setPreferredSize(new Dimension(280, 45));

        JButton btnUser = new JButton("User Login (No Password)");
        JButton btnAdmin = new JButton("Admin Login");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Dimension buttonSize = new Dimension(280, 55);

        btnUser.setFont(buttonFont);
        btnUser.setPreferredSize(buttonSize);
        btnUser.setBackground(new Color(2,77,134)); // Yellow
        btnUser.setForeground(Color.WHITE);
        btnUser.setFocusPainted(false);

        btnAdmin.setFont(buttonFont);
        btnAdmin.setPreferredSize(buttonSize);
        btnAdmin.setBackground(new Color(2,77,134)); // Yellow
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFocusPainted(false);

        // Layout for rightPanel
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(lblTitle, gbc);

        gbc.gridy++;
        rightPanel.add(usernameField, gbc);

        gbc.gridy++;
        rightPanel.add(btnUser, gbc);

        gbc.gridy++;
        rightPanel.add(btnAdmin, gbc);

        // Button actions
        btnUser.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new WelcomeScreen(frame));
            frame.revalidate();
            frame.repaint();
        });

        btnAdmin.addActionListener(e -> {
            String password = JOptionPane.showInputDialog(frame, "Enter Admin Password:");
            if ("admin123".equals(password)) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WelcomeAdminScreen(frame));
                frame.revalidate();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Assemble panels
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel, BorderLayout.CENTER);
    }
}
