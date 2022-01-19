package com.AHMED.Utilities;

public class GroceryItem {
    private String itemName;
    private double itemPrice;

    public GroceryItem(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "Name =' " + itemName + '\'' +
                "   Price = '" + itemPrice + '\'';
    }
}
