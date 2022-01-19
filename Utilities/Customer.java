package com.AHMED.Utilities;

import java.util.ArrayList;

public class Customer extends Member {
    private ArrayList<GroceryItem> items = new ArrayList<>();
    private double totalPurchase;
    private double amountGave;

    public Customer(String name, String phone, ArrayList<GroceryItem> items, double totalPurchase,double amountGave) {
        super(name, phone);
        this.items = items;
        this.totalPurchase = totalPurchase;
        this.amountGave = amountGave;
    }

    public double getAmountGave() {
        return amountGave;
    }

    public void setAmountGave(double amountGave) {
        this.amountGave = amountGave;
    }

    public ArrayList<GroceryItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GroceryItem> items) {
        this.items = items;
    }

    public double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", Items = " + items + '\'' +
                ", Total Purchase = " + totalPurchase +
                ", Amount Gave = " + amountGave;

    }
}
