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

public class requestadapter extends  RecyclerView.Adapter<requestadapter.myviewholder> {
    ArrayList<RequestModel> dataholder;
    Context context;

    public requestadapter(ArrayList<RequestModel> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    { LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singlerow2, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {

        final RequestModel s = dataholder.get(position);
        holder.dName.setText(s.getName());
        holder.dMOBILE_NUMBER.setText(s.getMOBILE_NUMBER());
        holder.dspinner.setText(dataholder.get(position).getSpinner());
        holder.dFULL_ADDRESS.setText(s.getFULL_ADDRESS());
        holder.dspinner.setText(s.getSpinner());
        holder.dtxtlocation.setText(s.getTxtLocation());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,  s.getName() ,Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context,UpdateRequestActivity.class);

                intent.putExtra("Request" , s);
                context.startActivity(intent);

            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation!!!");
                builder.setMessage("Are you sure to delete " + s.getName() + "?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int  which) {

                       Requestdata db = new Requestdata(context);
                        int  result = db.deleteModel(s.getName());
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
        TextView dName,dfoodType,dFULL_ADDRESS,dMOBILE_NUMBER,dspinner,dtxtlocation;
        Button edit ,Delete;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            dName=(TextView)itemView.findViewById(R.id.displayName);
            dMOBILE_NUMBER=(TextView)itemView.findViewById(R.id.displayMOBILE_NUMBER);
            dFULL_ADDRESS=(TextView)itemView.findViewById(R.id.displayFULL_ADDRESS);
            dfoodType=(TextView)itemView.findViewById(R.id.displaytypeoffood);
            dspinner=(TextView)itemView.findViewById(R.id.displaySpinner);
            dtxtlocation=(TextView)itemView.findViewById(R.id.displayCurrentLocation);
            edit = (Button) itemView.findViewById(R.id.Edit);
            Delete = (Button) itemView.findViewById(R.id.Delete);

        }
    }

}
