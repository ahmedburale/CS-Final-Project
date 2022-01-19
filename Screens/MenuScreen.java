package com.AHMED.Screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JFrame {

    private JPanel panel1;
    private static JButton reportButton;
    private static JButton productsButton;

    MenuScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 350, 350);
        panel1 = new JPanel();
        setVisible(true);
        setContentPane(panel1);
        setSize(350, 350);
        setResizable(true);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);


        reportButton = new JButton("Report");
        reportButton.setBounds(90, 60, 150, 70);
        panel1.add(reportButton);

        productsButton = new JButton("Manage Products");
        productsButton.setBounds(90, 160, 150, 70);
        panel1.add(productsButton);

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ReportScreen();
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ManageProducts();
            }
        });

        setVisible(true);
    }

}

