package com.example.mainsemester;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class requestadapter extends  RecyclerView.Adapter<requestadapter.myviewholder> {
    ArrayList<model2> dataholder;

    public requestadapter(ArrayList<model2> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow2,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.dName.setText(dataholder.get(position).getName());
        holder.dfoodType.setText(dataholder.get(position).getFoodType());
        holder.dFULL_ADDRESS.setText(dataholder.get(position).getFULL_ADDRESS());
        holder.dMOBILE_NUMBER.setText(dataholder.get(position).getMOBILE_NUMBER());
        holder.dspinner.setText(dataholder.get(position).getSpinner());

    }



    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView dName,dfoodType,dFULL_ADDRESS,dMOBILE_NUMBER,dspinner;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            dName=(TextView)itemView.findViewById(R.id.displayName);
            dMOBILE_NUMBER=(TextView)itemView.findViewById(R.id.displayMOBILE_NUMBER);
            dFULL_ADDRESS=(TextView)itemView.findViewById(R.id.displayFULL_ADDRESS);
            dfoodType=(TextView)itemView.findViewById(R.id.displayfoodType);
            dspinner=(TextView)itemView.findViewById(R.id.displaySpinner);

        }
    }

}
