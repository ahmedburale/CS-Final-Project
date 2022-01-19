package com.AHMED.Screens;

import com.AHMED.Utilities.GroceryItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LogIn extends JFrame {

    private static JPanel panel1;
    private static JTextField emailField;
    private static JTextField passwordField;
    private static JButton continueButton;

    LogIn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 350, 350);
        panel1 = new JPanel();
        setContentPane(panel1);
        setSize(350, 350);
        setResizable(true);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        emailField = new JTextField();
        emailField.setToolTipText("Enter your email");
        emailField.setBounds(90, 70, 150, 25);
        panel1.add(emailField);

        passwordField = new JTextField();
        passwordField.setToolTipText("Enter your password");
        passwordField.setBounds(90, 150, 150, 25);
        panel1.add(passwordField);

        continueButton = new JButton("Log In");
        continueButton.setBounds(120, 210, 90, 25);
        panel1.add(continueButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(120, 260, 90, 25);
        panel1.add(signUpButton);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if (email.isBlank() || email.isEmpty() ||
                        password.isBlank() || password.isBlank()
                ) {
                    JOptionPane.showMessageDialog(null,
                            "Enter in the fields. Fields shouldn't be empty",
                            "Fields required",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (checkAuthorized(email, password)) {
                        dispose();
                        new MenuScreen();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Check your email or password",
                                "Email or Password Incorrect",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if (email.isBlank() || email.isEmpty() ||
                        password.isBlank() || password.isBlank()
                ) {
                    JOptionPane.showMessageDialog(null,
                            "Enter in the fields. Fields shouldn't be empty",
                            "Fields required",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                        signUp(email,password);
                    JOptionPane.showMessageDialog(null,
                            "Account has been created, Enter email or password again to log in",
                            "Account Created",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });

        setVisible(true);
    }

    private boolean checkAuthorized(String mail, String pswd) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("src\\com\\AHMED\\TextFiles\\managers.txt"));
            scanner.useDelimiter(", ");
            while (scanner.hasNextLine()) {
                String email = scanner.next();
                scanner.skip(scanner.delimiter());
                String password = scanner.nextLine();

                if (email.equals(mail) && password.equals(pswd)) {
                    return true;
                }

            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return false;
    }

    private void signUp(String email, String password) {
        ArrayList<GroceryItem> items = new ArrayList<>();
        try (FileWriter fw = new FileWriter("src\\com\\AHMED\\TextFiles\\managers.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(email + ", " + password);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
