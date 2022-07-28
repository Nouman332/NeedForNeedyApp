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

public class NGOmyadapter  extends RecyclerView.Adapter<NGOmyadapter.myviewholder> {
    ArrayList<NGOmodel> dataholder;
    Context context;

    public NGOmyadapter(ArrayList<NGOmodel> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }


    @NonNull
    @Override
    public NGOmyadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ngo_singlerow, parent, false);
       return new NGOmyadapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NGOmyadapter.myviewholder holder, @SuppressLint("RecyclerView") int position) {
        final NGOmodel s = dataholder.get(position);
        holder.NGOname.setText(s.getNGO_name());
        holder.etWebsite.setText(s.getEtWebsite());
        holder.email.setText(s.getEmail());
        holder.phone.setText(s.getPhone());
        holder.etAddress.setText(s.getEtAddress());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,  s.getName() ,Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context,UpdateNGOActivity.class);

                intent.putExtra("NGO" , s);
                context.startActivity(intent);

            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation!!!");
                builder.setMessage("Are you sure to delete " + s.getNGO_name() + "?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int  which) {

                       NGOdatabase db = new NGOdatabase(context);
                        int  result = db.deleteModel(s.getNGO_name());
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
    } class myviewholder extends RecyclerView.ViewHolder
    {
        TextView NGOname,etWebsite,email,phone,etAddress;
        Button edit ,Delete;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
           NGOname=(TextView)itemView.findViewById(R.id.displayNGOName);
            etWebsite=(TextView)itemView.findViewById(R.id.displayWebSite);
           email=(TextView)itemView.findViewById(R.id.displayEmail);
            phone=(TextView)itemView.findViewById(R.id.displayContactNo);
            etAddress=(TextView)itemView.findViewById(R.id.displayHomeAddress);
            edit = (Button) itemView.findViewById(R.id.Edit);
            Delete = (Button) itemView.findViewById(R.id.Delete);

        }
    }
}
