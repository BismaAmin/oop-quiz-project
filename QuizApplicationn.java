package com.mycompany.quizapplicationn;

import java.awt.BorderLayout;
import javax.swing.*;

public class QuizApplicationn  {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("MindVoyage Quiz Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500); // Set appropriate size
            frame.setLocationRelativeTo(null); // Center the window

            // Create a content panel and add the LoginScreen to it
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.add(new LoginScreen(frame), BorderLayout.CENTER);

            frame.setContentPane(contentPanel); // Set the content pane
            frame.setVisible(true);
        });
    }
}
