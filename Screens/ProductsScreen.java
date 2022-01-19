package com.AHMED.Screens;

import com.AHMED.Utilities.Customer;
import com.AHMED.Utilities.GroceryItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsScreen extends JFrame {
    private JPanel panel1;
    private JLabel label;
    private JLabel label2;
    private JLabel totalPriceLabel;
    private JTable cartTable;
    private JLabel changeLabel;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField paymentField;
    private JList<GroceryItem> productsList;
    private DefaultListModel<GroceryItem> groceryItemModel = new DefaultListModel<>();
    private DefaultTableModel tableModel;
    private JButton paymentButton;
    private JButton manageButton;
    private double totalPrice = 0;

    public ProductsScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 900, 600);
        panel1 = new JPanel();
        setContentPane(panel1);
        setResizable(true);
        setLayout(null);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setProductsList();

        label = new JLabel();
        label.setText("Total Products");
        label.setBounds(120, 5, 200, 25);
        panel1.add(label);

        productsList = new JList<>(groceryItemModel);
        productsList.setBounds(15, 30, 340, 400);

        productsList.setModel(groceryItemModel);
        panel1.add(productsList);

        JScrollPane scrollPane = new JScrollPane(productsList,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(productsList.getX(), productsList.getY(), productsList.getWidth(), productsList.getHeight());
        panel1.add(scrollPane);

        productsList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    GroceryItem selectedValue = productsList.getSelectedValue();
                    double price = selectedValue.getItemPrice();
                    Object[] object = {selectedValue.getItemName(), price};
                    tableModel.addRow(object);
                    totalPrice += price;
                    totalPriceLabel.setText(String.valueOf(totalPrice));
                }
            }
        });

        manageButton = new JButton("Manage Customers");
        manageButton.setBounds(90, 450, 170, 25);
        panel1.add(manageButton);


        label2 = new JLabel();
        label2.setText("Cart List");
        label2.setBounds(660, 5, 200, 25);
        panel1.add(label2);

        String[] column = {"Name", "Price"};
        tableModel = new DefaultTableModel(null, column);
        cartTable = new JTable(tableModel);
        cartTable.setBounds(550, 30, 300, 300);
        panel1.add(cartTable);

        JScrollPane tablePane = new JScrollPane(cartTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePane.setBounds(cartTable.getX(), cartTable.getY(), cartTable.getWidth(), cartTable.getHeight());
        panel1.add(tablePane);


        JLabel totalLable = new JLabel();
        totalLable.setText("Total Price : ");
        totalLable.setBounds(550, 340, 150, 25);
        panel1.add(totalLable);

        totalPriceLabel = new JLabel();
        totalPriceLabel.setText("0");
        totalPriceLabel.setBounds(680, 340, 150, 25);
        panel1.add(totalPriceLabel);

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Customer Name : ");
        nameLabel.setBounds(550, 370, 150, 25);
        panel1.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(680, 370, 150, 25);
        panel1.add(nameField);

        JLabel phoneLabel = new JLabel();
        phoneLabel.setText("Customer Phone : ");
        phoneLabel.setBounds(550, 400, 150, 25);
        panel1.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(680, 400, 150, 25);
        panel1.add(phoneField);

        JLabel paymentLabel = new JLabel();
        paymentLabel.setText("Enter payment : ");
        paymentLabel.setBounds(550, 430, 150, 25);
        panel1.add(paymentLabel);

        paymentField = new JTextField();
        paymentField.setBounds(680, 430, 150, 25);
        panel1.add(paymentField);

        JLabel cLabel = new JLabel();
        cLabel.setText("Change");
        cLabel.setBounds(550, 460, 150, 25);
        panel1.add(cLabel);

        changeLabel = new JLabel("0");
        changeLabel.setBounds(680, 460, 150, 25);
        panel1.add(changeLabel);


        paymentButton = new JButton("Payment");
        paymentButton.setBounds(630, 500, 130, 25);
        panel1.add(paymentButton);


        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();

                if (name.isEmpty() || phone.isEmpty() || paymentField.getText().isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "Fields should not be empty", "Fields Required", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                onPaymentButtonClick(name, phone);
            }
        });

        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogIn();
            }
        });

        setVisible(true);

        paymentField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                setChangeLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

    }

    private void setProductsList() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("src\\com\\AHMED\\TextFiles\\products.txt"));
            scanner.useDelimiter(", ");
            while (scanner.hasNextLine()) {
                String name = scanner.next();
                scanner.skip(scanner.delimiter());
                String price = scanner.nextLine();
                groceryItemModel.addElement(new GroceryItem(name, Double.valueOf(price)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private void addingCustomers(String cName, String cPhone) {
        ArrayList<GroceryItem> items = new ArrayList<>();
        try (FileWriter fw = new FileWriter("src\\com\\HAMLET\\TextFiles\\customers.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for (int i = 0; i < cartTable.getRowCount(); i++) {
                String itemName = String.valueOf(cartTable.getValueAt(i, 0));
                String itemPrice = String.valueOf(cartTable.getValueAt(i, 1));
                items.add(new GroceryItem(itemName, Double.parseDouble(itemPrice)));
            }

            double amountGave = Double.parseDouble(paymentField.getText().toString());
            Customer customer = new Customer(cName,cPhone,items,totalPrice,amountGave);
            out.println(customer);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void onPaymentButtonClick(String name, String phone) {
        addingCustomers(name, phone);
        totalPrice = 0;
        paymentField.setText("0");
        totalPriceLabel.setText(String.valueOf(totalPrice));
        nameField.setText("");
        phoneField.setText("");
        DefaultTableModel dtm = (DefaultTableModel) cartTable.getModel();
        dtm.setRowCount(0);
    }

    private void setChangeLabel() {

        double givenPrice = Double.parseDouble(paymentField.getText().toString());
        double totalPrice = Double.parseDouble(totalPriceLabel.getText().toString());

        double c = givenPrice - totalPrice;
        if(c < 1){
            changeLabel.setText(String.valueOf(0));
        } else {
            changeLabel.setText(String.valueOf(c));
        }
    }

}
