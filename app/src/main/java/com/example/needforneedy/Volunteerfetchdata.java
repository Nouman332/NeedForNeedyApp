package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class Volunteerfetchdata extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<VolunteerModel> dataholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteerfetchdata);

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new volunteerdatabase(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
            VolunteerModel obj = new VolunteerModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            dataholder.add(obj);
        }

       volunteeradapter adapter=new volunteeradapter(dataholder,this);
        recyclerView.setAdapter(adapter);


    }
}