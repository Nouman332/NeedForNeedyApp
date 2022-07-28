package com.example.needforneedy.;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fetchdata extends AppCompatActivity
{

    RecyclerView recyclerView;
    ArrayList<model> dataholder;

    @Override


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata);

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new Donatefood(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
        model obj1 =new model(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
         dataholder.add(obj1);
        }


        myadapter adapter=new myadapter(dataholder);
        recyclerView.setAdapter(adapter);

    }
}