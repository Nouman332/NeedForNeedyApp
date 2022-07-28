package com.example.needforneedy;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Monetarymyadapter extends RecyclerView.Adapter<Monetarymyadapter.myviewholder>{


    ArrayList<MonetaryModel> dataholder;
    Context context;

    public Monetarymyadapter(ArrayList<MonetaryModel> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }



    @NonNull
    @Override
    public Monetarymyadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donation_singlerow, parent, false);
        Monetarymyadapter.myviewholder myviewholder = new Monetarymyadapter.myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Monetarymyadapter.myviewholder holder, @SuppressLint("RecyclerView") int position) {
        final MonetaryModel s  = dataholder.get(position);
        holder.dDonation.setText(s.getEtAmount());
        holder.dName.setText(s.getEtName());
        holder.dContactNumber.setText(s.getEtNumber());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,  s.getName() ,Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context,UpdateMonetaryActivity.class);

                intent.putExtra("Monetary" , s);
                context.startActivity(intent);

            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation!!!");
                builder.setMessage("Are you sure to delete " + s.getEtName() + "?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int  which) {

                       Monetarydb db = new Monetarydb(context);
                        int  result = db.deleteModel(s.getEtName());
                        if(result!=-1){
                            Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                            dataholder.remove(position);
                            notifyDataSetChanged();

                        }else{
                            Toast.makeText(context,"Deleted not Successfully",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton( "No",null);
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView dDonation,dName,dContactNumber;
        Button edit ,Delete;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            dDonation=(TextView)itemView.findViewById(R.id.displayDonations);
            dName=(TextView)itemView.findViewById(R.id.displayFullName);
            dContactNumber=(TextView)itemView.findViewById(R.id.displayContactNumber);
            edit = (Button) itemView.findViewById(R.id.Edit);
            Delete = (Button) itemView.findViewById(R.id.Delete);
        }
    }
}

