package com.example.restardfyp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SummaryAdapter extends ArrayAdapter<Customer> {
    Context context;
    ArrayList<Customer> items;
    int resource;
    TextView tvName, tvQuantity, tvPrice, tvRemove;


    public SummaryAdapter(Context context, int resource, ArrayList<Customer> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.summarylist, parent, false);

        //-----------------Casting------------------
        tvName = rowView.findViewById(R.id.tvItemName);
        tvQuantity = rowView.findViewById(R.id.tvQuantity);
        tvPrice = rowView.findViewById(R.id.tvPrice);
        tvRemove = rowView.findViewById(R.id.tvRemove);
        //-----------------Casting------------------


        //------------------Getting--------------
        Customer customer = items.get(position);
        String name = customer.getItemName();
        double price = customer.getItemPrice();
        int quantity = customer.getQuantity();
        int id = customer.get_id();
        //------------------Getting--------------

        //-------------------Setup-----------------
        tvName.setText(name);
        tvPrice.setText("$" + price);
        tvQuantity.setText(quantity + " Packet");
        //-------------------Setup-----------------


        //=========================RemoveButtonHandle========================
        tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBCustomer db = new DBCustomer(context);
                db.deleteCusItem(id);
                db.close();
            }
        });
        //=========================RemoveButtonHandle========================

        return rowView;
    }
}
