package com.example.needforneedy.;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
  ArrayList<model> dataholder;

    public myadapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.dName.setText(dataholder.get(position).getName());
        holder.dtyeoffood.setText(dataholder.get(position).getTypeOfFood());

        holder.dFULL_ADDRESS.setText(dataholder.get(position).getFULL_ADDRESS());
        holder.dMOBILE_NUMBER.setText(dataholder.get(position).getMOBILE_NUMBER());
  holder.dspinner.setText(dataholder.get(position).getSpinner());
  holder.dfoodDrop.setText(dataholder.get(position).getFoodDrop());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView dName,dtyeoffood,dFULL_ADDRESS,dMOBILE_NUMBER,dfoodDrop,dspinner;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            dName=(TextView)itemView.findViewById(R.id.displayName);
            dMOBILE_NUMBER=(TextView)itemView.findViewById(R.id.displayMOBILE_NUMBER);
           dFULL_ADDRESS=(TextView)itemView.findViewById(R.id.displayFULL_ADDRESS);
            dtyeoffood=(TextView)itemView.findViewById(R.id.displaytypeoffood);
          dfoodDrop=(TextView)itemView.findViewById(R.id.displayradiobutton);
             dspinner=(TextView)itemView.findViewById(R.id.displaySpinner);

        }
    }

}
