package com.AHMED.Screens;

import com.AHMED.Utilities.GroceryItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class ManageProducts extends JFrame {
    private JPanel panel1;
    private JList<GroceryItem> productsList;
    private JTextField itemName;
    private JTextField itemPrice;

    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    private DefaultListModel<GroceryItem> groceryItemModel = new DefaultListModel<>();

    ManageProducts() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 150, 700, 500);
        panel1 = new JPanel();
        setContentPane(panel1);
        setResizable(true);
        setLayout(null);
        setVisible(true);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setProductsList();

        JLabel label = new JLabel();
        label.setText("All Products");
        label.setBounds(430, 5, 100, 20);
        panel1.add(label);

        productsList = new JList<>(groceryItemModel);
        productsList.setBounds(320, 30, 340, 400);
        productsList.setModel(groceryItemModel);
        panel1.add(productsList);

        itemName = new JTextField();
        itemName.setToolTipText("Enter item name");
        itemName.setBounds(80, 100, 150, 25);
        panel1.add(itemName);

        itemPrice = new JTextField();
        itemPrice.setToolTipText("Enter item price");
        itemPrice.setBounds(80, 150, 150, 25);
        panel1.add(itemPrice);

        addButton = new JButton("Add");
        addButton.setBounds(20, 240, 70, 25);
        panel1.add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 240, 70, 25);
        panel1.add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(180, 240, 90, 25);
        panel1.add(updateButton);

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
                    itemName.setText(selectedValue.getItemName());
                    itemPrice.setText(String.valueOf(selectedValue.getItemPrice()));
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemName.getText().toString();
                double price = Double.parseDouble(itemPrice.getText().toString());
                GroceryItem groceryItem = new GroceryItem(name, price);

                groceryItemModel.addElement(groceryItem);
                try {
                    settingProductsFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemName.getText().toString();
                int index = getIndexOf(name);
                if (index + 1 == groceryItemModel.size()) {
                    productsList.setSelectedIndex(0);
                } else {
                    productsList.setSelectedIndex(index + 1);
                }
                groceryItemModel.removeElementAt(index);
                try {
                    settingProductsFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemName.getText().toString();
                double price = Double.parseDouble(itemPrice.getText().toString());
                GroceryItem groceryItem = new GroceryItem(name, price);
                int index = getIndexOf(name);
                if (index == -1) {
                    JOptionPane.showMessageDialog(null,
                            "You cannot update this item, or name of item",
                            "Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    groceryItemModel.set(index, groceryItem);
                    try {
                        settingProductsFile();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 400, 70, 25);
        panel1.add(backButton);
        validate();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProductsScreen();
            }
        });

        setVisible(true);
    }

    private int getIndexOf(String itemName) {
        for (int i = 0; i < groceryItemModel.size(); i++) {
            GroceryItem groceryItem = groceryItemModel.get(i);
            if (groceryItem.getItemName().equals(itemName)) {
                return groceryItemModel.indexOf(groceryItem);
            }
        }
        return -1;
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

    private void settingProductsFile() throws IOException {
        FileWriter locFile = new FileWriter("src\\com\\AHMED\\TextFiles\\products.txt");

        try {
            for(int i=0; i<groceryItemModel.size(); i++){
                GroceryItem groceryItem = groceryItemModel.get(i);
                locFile.write(groceryItem.getItemName() + ", " + groceryItem.getItemPrice() + "\n");
            }

        } catch (IOException e) {
            System.out.println("In catch block");
            e.printStackTrace();
        } finally {
            try {
                if (locFile != null) {
                    locFile.close();
                }
            } catch (IOException e) {
                System.out.println("in finally block");
                e.printStackTrace();
            }
        }
    }

}
