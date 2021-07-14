package com.example.restardfyp;
import java.io.Serializable;


//=====================HandleProducts======================
public class Item implements Serializable {
    private int _id;
    String itemName;
    String itemDetails;
    int itemPhoto;
    double itemPrice;

    public Item(int _id, String itemName, String itemDetails, int itemPhoto, double itemPrice) {
        this._id=_id;
        this.itemName = itemName;
        this.itemDetails = itemDetails;
        this.itemPhoto = itemPhoto;
        this.itemPrice = itemPrice;
    }

    public int get_id() {
        return _id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public int getItemPhoto() {
        return itemPhoto;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
//=====================HandleProducts======================