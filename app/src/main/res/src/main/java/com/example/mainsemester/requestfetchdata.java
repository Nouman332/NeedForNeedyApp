package com.example.mainsemester;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class requestfetchdata extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<model2> dataholder;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestfetchdata);

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new Requestdata(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
          model2 obj = new model2(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
           dataholder.add(obj);
        }

        requestadapter adapter=new requestadapter(dataholder);
        recyclerView.setAdapter(adapter);
    }
}