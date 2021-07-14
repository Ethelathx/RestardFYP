package com.example.restardfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Infolist extends AppCompatActivity {

    ImageView imageView;
    ImageButton plusQuantity, minusQuantity;
    TextView quantityNumber, itemName, itemPrice, itemDetails, pricePerPack, priceDesc;

    Button addToCart;
    int quantity = 1;
    Item data;
    Customer CusData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infolist);

        //===================Casting=====================
        itemDetails = findViewById(R.id.descriptioninfo);
        imageView = findViewById(R.id.imageViewInfo);
        plusQuantity = findViewById(R.id.addquantity);
        minusQuantity  = findViewById(R.id.subquantity);
        quantityNumber = findViewById(R.id.quantity);
        addToCart = findViewById(R.id.addtocart);
        itemName = findViewById(R.id.ItemNameinInfo);
        itemPrice = findViewById(R.id.ItemPrice);
        pricePerPack = findViewById(R.id.PricePerPack);
        priceDesc = findViewById(R.id.ItemPriceDescription);
        //===================Casting=====================

        //------------------GetIntentData---------------------
        Intent i = getIntent();
        data = (Item) i.getSerializableExtra("data");
        //------------------GetIntentData---------------------


        //------------------UISetup-------------
        quantityNumber.setText(String.valueOf(quantity));
        imageView.setImageResource(data.getItemPhoto());
        itemName.setText(data.getItemName());
        itemDetails.setText(data.getItemDetails());
        itemPrice.setText(String.valueOf(data.getItemPrice()));
        priceDesc.setText("Total Cost: $");
        pricePerPack.setText("Price per pack: $" + data.getItemPrice());
        //------------------UISetup-------------


        //==========================PlusButton============================
        plusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double basePrice = data.itemPrice;
                quantity++;
                displayQuantity();
                double ItemPrice = basePrice * quantity;
                String setNewPrice = String.valueOf(ItemPrice);
                itemPrice.setText(setNewPrice);
            }
        });
        //==========================PlusButton============================


        //==========================MinusButton============================
        minusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double basePrice = data.itemPrice;
                if (quantity < 1) {
                    Toast.makeText(Infolist.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();


                } else {
                    quantity--;
                    displayQuantity();
                    double ItemPrice = basePrice * quantity;
                    String setNewPrice = String.valueOf(ItemPrice);
                    itemPrice.setText(setNewPrice);
                }
            }
        });
        //==========================MinusButton============================


        //===========================AddToCart=============================
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(itemPrice);

                //------------------AddCusDataBase-------------------
                //------------------DataToBeAdded--------------------
                String pname = itemName.getText().toString();
                String cname = "EmptyUserName";
                int quantity = Integer.parseInt(quantityNumber.getText().toString());
                double price = Double.parseDouble(itemPrice.getText().toString());
                //------------------DataToBeAdded--------------------


                if (quantity >= 1) {
                    DBCustomer dbc = new DBCustomer(Infolist.this);
                    long inserted_id = dbc.insertCustomer(pname, cname, quantity, price);
                    dbc.close();
                    //------------------AddCusDataBase-------------------


                    Intent intent = new Intent(Infolist.this, Summarylist.class);
                    startActivity(intent);

                    if (inserted_id != -1) {
                        Toast.makeText(Infolist.this, "Success adding to Cart",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Infolist.this, "Failed to add to Cart",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                //===========================CatchPreventAdd0===================
                else {
                    Toast.makeText(Infolist.this, "Quantity cannot be 0",
                            Toast.LENGTH_SHORT).show();
                }
                //===========================CatchPreventAdd0===================


            }
        });
        //===========================AddToCart=============================

    }

    private void displayQuantity() {
        quantityNumber.setText(String.valueOf(quantity));
    }

}