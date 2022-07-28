package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class DonateFetchdta extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Donatemodel> dataholder;
    Donatefood db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_fetchdta);
        recyclerView=(RecyclerView)findViewById(R.id.recview);
        db = new Donatefood(DonateFetchdta.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new Donatefood(this).readalldata();
        dataholder=new ArrayList<>();


        while(cursor.moveToNext())
        {
            Donatemodel obj1 =new Donatemodel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
            dataholder.add(obj1);
        }


        Donateadapter adapter=new Donateadapter(dataholder,this);
        recyclerView.setAdapter(adapter);
    }
}