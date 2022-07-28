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

public class volunteeradapter extends RecyclerView.Adapter<volunteeradapter.myviewholder> {
    ArrayList<VolunteerModel> dataholder;
    Context context;

    public volunteeradapter(ArrayList<VolunteerModel> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.volunteer_singlerow, parent, false);
      volunteeradapter.myviewholder myviewholder = new volunteeradapter.myviewholder(view);
        return myviewholder;

    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {
        final VolunteerModel s = dataholder.get(position);


        holder.firstName.setText(s.getFirstName());
        holder.lastName.setText(s.getLastName());
        holder.email.setText(s.getEmail());
        holder.phone.setText(s.getPhone());
        holder.Password.setText(s.getPassword());;
        holder.ConfirmPassword.setText(s.getConfirmPassword());
        holder.weekday.setText(s.getWeekday());
        holder.spinner.setText(s.getSpinner());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,  s.getName() ,Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context,UpdateVolunteerActivity.class);

                intent.putExtra("Volunteer" , s);
                context.startActivity(intent);

            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation!!!");
                builder.setMessage("Are you sure to delete " + s.getFirstName() + "?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int  which) {

                        volunteerdatabase db = new volunteerdatabase(context);
                        int  result = db.deleteModel(s.getFirstName());
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
        TextView firstName,lastName, email, phone, Password , ConfirmPassword ,weekday,spinner;
        Button edit ,Delete;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
           firstName=(TextView)itemView.findViewById(R.id.displayFullName);
            lastName=(TextView)itemView.findViewById(R.id.displayUsername);
            email=(TextView)itemView.findViewById(R.id.displayEmail);
            phone=(TextView)itemView.findViewById(R.id.displayPhoneNo);
           Password=(TextView)itemView.findViewById(R.id.displayPassword);
            ConfirmPassword=(TextView)itemView.findViewById(R.id.displayConfirmPassword);
            weekday=(TextView)itemView.findViewById(R.id.displayCheckbox);
            spinner=(TextView)itemView.findViewById(R.id.displaySpinner);
            edit = (Button) itemView.findViewById(R.id.Edit);
            Delete = (Button) itemView.findViewById(R.id.Delete);

        }
    }
}
