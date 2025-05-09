/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author User
 */
public class Rules extends JFrame implements ActionListener {
    String name;
    JButton start,back;
    Rules(String name){
        this.name = name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel(" WELCOME " + name + " TO SIMPLE MINDS") ;
        heading.setBounds(30,20,700,30);
        heading.setFont(new Font("Mogolian Baiti", Font.BOLD,20));
        heading.setForeground(new Color(161, 197, 53));
        add(heading);
        
        JLabel rules = new JLabel() ;
        rules.setBounds(20,90,700,350);
        rules.setFont(new Font("Tahoma", Font.PLAIN,16));
        rules.setText(
          "<html>"+
                  "1. Each question must be answered within the allocated time." + "<br><br>"+
                  "2. Once you move to the next question, you cannot return to the previous one."+ "<br><br>" +
                  "3. Each user is allowed only one attempt per quiz." + "<br><br>"+
                  "4. Use of external resources is not allowed during the quiz."+ "<br><br>" +
                  "5. If the time runs out, the quiz will be submitted automatically." + "<br><br>"+
                  "6. Each correct answer awards points; incorrect answers may give zero or deduct points." + "<br><br>"+
                  "7. A minimum score is required to pass the quiz." + "<br><br>"+
                  "8. Suspicious or dishonest behavior may lead to disqualification."+ "<br><br>"+
          "<html>" 
        );
        add(rules);
        
        back = new JButton ("Back");
        back.setBounds(250,500,100,30);
        back.setBackground(new Color(30,144,254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        start = new JButton ("Start");
        start.setBounds(400,500,100,30);
        start.setBackground(new Color(30,144,254));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);
        
        setSize(800, 650);
        setLocation(350,100);
        setVisible(true);
    }
    
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource()== start){
        }
        else {
            setVisible(false);
            new Login();
        }
        
        
    }
    public static void main (String [] args){
        new Rules("User");
    }
}
