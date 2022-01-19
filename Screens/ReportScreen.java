package com.AHMED.Screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


public class ReportScreen extends JFrame {
    private JPanel panel1;
    private JLabel label;
    private JButton backButton;

    private JList<String> customersList;
    private DefaultListModel<String> customerModel = new DefaultListModel<>();

    ReportScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 900, 530);
        panel1 = new JPanel();
        setVisible(true);
        setContentPane(panel1);
        setResizable(true);
        setLayout(null);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setProductsList();

        label = new JLabel();
        label.setText("All Customers");
        label.setBounds(400, 5, 200, 25);
        panel1.add(label);

        customersList = new JList<>(customerModel);
        customersList.setBounds(35, 50, 830, 400);

        customersList.setModel(customerModel);
        panel1.add(customersList);

        JScrollPane scrollPane = new JScrollPane(customersList,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(customersList.getX(), customersList.getY(), customersList.getWidth(), customersList.getHeight());
        panel1.add(scrollPane);

        backButton = new JButton("Back");
        backButton.setBounds(400,455,90,30);
        panel1.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProductsScreen();
            }
        });

        setVisible(true);
    }

    private void setProductsList() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("src\\com\\AHMED\\TextFiles\\customers.txt"));
            while (scanner.hasNextLine()) {
                String customer = scanner.nextLine();
                customerModel.addElement(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}

