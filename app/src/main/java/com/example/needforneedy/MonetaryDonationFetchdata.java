package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class MonetaryDonationFetchdata extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MonetaryModel> dataholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monetary_donation_fetchdata);

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new Monetarydb(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
            MonetaryModel obj1 =new MonetaryModel(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataholder.add(obj1);
        }


        Monetarymyadapter adapter=new Monetarymyadapter(dataholder,this);
        recyclerView.setAdapter(adapter);
    }
}