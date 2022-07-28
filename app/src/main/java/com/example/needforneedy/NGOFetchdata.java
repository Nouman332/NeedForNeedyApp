package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class NGOFetchdata extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<NGOmodel> dataholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngofetchdata);
        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new NGOdatabase(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
            NGOmodel obj = new NGOmodel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            dataholder.add(obj);
        }

        NGOmyadapter adapter=new NGOmyadapter(dataholder,this);
        recyclerView.setAdapter(adapter);

    }
}