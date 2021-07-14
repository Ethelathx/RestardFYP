package com.example.restardfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Item> newItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------Casting---------------
        lv = (ListView) this.findViewById(R.id.lvItems);

        String name = "Epiros Original Feta (PDO) Reduced Salt";
        String details = "Made from 100% Greek Sheep’s & Goat’s milk\n" +
                "Contains 40% less salt compared to the Epiros Original Feta recipe, without compromising its award winning rich and authentic taste\n" +
                "Contains natural salt and no substitutes, with a 30g portion containing  just 0.4g salt\n" +
                "200g";
        int photo = R.drawable.ic_launcher_foreground;
        double price = 10;
        //-------------Casting---------------


        //-------------------DBSetupArray-------------------
        DBDisplay dbh = new DBDisplay(MainActivity.this);
        newItems = new ArrayList<Item>();
        //long inserted_id = dbh.InsertItem(name,details,photo,price);
        newItems.clear();
        newItems.addAll(dbh.getAllItems());
        dbh.close();

        aa = new ItemAdapter(MainActivity.this,R.layout.itemlist,newItems);
        lv.setAdapter(aa);
        //-------------------DBSetupArray-------------------


        //---------------------LVClickHandle------------------------
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Item target = newItems.get(position);
                Intent i = new Intent(MainActivity.this,Infolist.class);
                i.putExtra("data", target);
                startActivityForResult(i,9);
            }
        });
        //---------------------LVClickHandle------------------------

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBDisplay dbh = new DBDisplay(MainActivity.this);
            newItems.clear();
            newItems.addAll(dbh.getAllItems());
            dbh.close();

            aa.notifyDataSetChanged();
        }
    }

}