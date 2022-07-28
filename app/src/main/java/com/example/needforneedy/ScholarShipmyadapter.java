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

public class ScholarShipmyadapter extends RecyclerView.Adapter<ScholarShipmyadapter.myviewholder> {
    ArrayList<ScholarShipModel> dataholder;
    Context context;

    public ScholarShipmyadapter(ArrayList<ScholarShipModel> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }


    @NonNull
    @Override
    public ScholarShipmyadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.scholarship_singlerow, parent, false);
         return new ScholarShipmyadapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarShipmyadapter.myviewholder holder, @SuppressLint("RecyclerView") int position) {

        final ScholarShipModel s = dataholder.get(position);
        holder.etFullName.setText(s.getEtFullName());
        holder.etFatherName.setText(s.getEtFatherName());
        holder.etCNIC.setText(s.getEtCNIC());
        holder.etPhoneNo.setText(s.getEtPhoneNo());
        holder.etEmail.setText(s.getEtEmail());;
        holder.etHomeAddress.setText(s.getEtHomeAddress());
        holder.etGender.setText(s.getEtGender());
        holder.etMatricMarks.setText(s.getEtMatricMarks());
        holder.etFSC.setText(s.getEtFSC());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,  s.getName() ,Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context,UpdateScholarActivity.class);

                intent.putExtra("ScholarShip" , s);
                context.startActivity(intent);

            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation!!!");
                builder.setMessage("Are you sure to delete " + s.getEtFullName() + "?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int  which) {

                      ScholarShipdb db = new ScholarShipdb(context);
                        int  result = db.deleteModel(s.getEtFullName());
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
        TextView etFullName, etFatherName,etCNIC,etPhoneNo,etEmail,etHomeAddress,etGender,etMatricMarks,etFSC;
        Button edit ,Delete;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            etFullName=(TextView)itemView.findViewById(R.id.displayFullName);
           etFatherName=(TextView)itemView.findViewById(R.id.displayFatherName);
           etCNIC=(TextView)itemView.findViewById(R.id.displayCNICNO);
          etPhoneNo=(TextView)itemView.findViewById(R.id.displayPhoneNo);
           etEmail=(TextView)itemView.findViewById(R.id.displayEmail);
           etHomeAddress=(TextView)itemView.findViewById(R.id.displayHomeAddress);
            etGender=(TextView)itemView.findViewById(R.id.displayGender);
            etMatricMarks=(TextView)itemView.findViewById(R.id.displayMatric);
            etFSC=(TextView)itemView.findViewById(R.id.displayFSC);
            edit = (Button) itemView.findViewById(R.id.Edit);
            Delete = (Button) itemView.findViewById(R.id.Delete);

        }
    }
}
