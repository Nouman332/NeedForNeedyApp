package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class ScholarShipFetchdata extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ScholarShipModel> dataholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholar_ship_fetchdata);
        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new ScholarShipdb(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
           ScholarShipModel obj1 =new ScholarShipModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
            dataholder.add(obj1);
        }


       ScholarShipmyadapter adapter=new ScholarShipmyadapter(dataholder,this);
        recyclerView.setAdapter(adapter);
    }
}