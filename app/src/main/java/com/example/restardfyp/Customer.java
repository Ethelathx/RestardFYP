package com.example.restardfyp;
import java.io.Serializable;


//=====================HandleCustomerOrder======================
public class Customer implements Serializable {
    private int _id;
    String itemName;
    String customerName;
    int quantity;
    double itemPrice;

    public Customer(int _id, String itemName, String customerName, int quantity, double itemPrice) {
        this._id = _id;
        this.itemName = itemName;
        this.customerName = customerName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public int get_id() {
        return _id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
//=====================HandleCustomerOrder======================