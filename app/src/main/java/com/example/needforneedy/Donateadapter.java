package com.example.needforneedy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Donateadapter extends RecyclerView.Adapter<Donateadapter.myviewholder> {
    ArrayList<Donatemodel> dataholder;
    Context context;

    public Donateadapter(ArrayList<Donatemodel> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public Donateadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singlerow, parent, false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Donateadapter.myviewholder holder, @SuppressLint("RecyclerView") int position) {
        final Donatemodel s = dataholder.get(position);
        holder.dName.setText(s.getName());
        holder.dMOBILE_NUMBER.setText(s.getMOBILE_NUMBER());
        holder.dspinner.setText(s.getSpinner());
        holder.dFULL_ADDRESS.setText(s.getFULL_ADDRESS());
        holder.dtyeoffood.setText(s.getTypeOfFood());
        holder.dtxtlocation.setText(s.getTxtLocation());
        holder.dfoodDrop.setText(s.getFoodDrop());



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,  s.getName() ,Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context,UpdateDonateActivity.class);

                intent.putExtra("Donates" , s);
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

                        Donatefood db = new Donatefood(context);
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

        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "http://192.168.100.15/android_pool/gen_token.php"; //using this IP for Genymotion emulator
                new updateData().execute(link);
//                Toast.makeText(context, "Executed", Toast.LENGTH_LONG).show();
            }

        });


    }




    @Override
    public int getItemCount() {
        return dataholder.size();
    } class myviewholder extends RecyclerView.ViewHolder
    {
        TextView dName,dtyeoffood,dFULL_ADDRESS,dMOBILE_NUMBER,dfoodDrop,dtxtlocation,dspinner;
        Button edit ,Delete,Accept;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            dName=(TextView)itemView.findViewById(R.id.displayName);
            dMOBILE_NUMBER=(TextView)itemView.findViewById(R.id.displayMOBILE_NUMBER);
            dFULL_ADDRESS=(TextView)itemView.findViewById(R.id.displayFULL_ADDRESS);
            dtyeoffood=(TextView)itemView.findViewById(R.id.displaytypeoffood);
            dfoodDrop=(TextView)itemView.findViewById(R.id.displayradiobutton);
            dtxtlocation=(TextView)itemView.findViewById(R.id.displayCurrentLocation);
            dspinner = (TextView) itemView.findViewById(R.id.displaySpinner);
            edit = (Button) itemView.findViewById(R.id.Edit);
            Delete = (Button) itemView.findViewById(R.id.Delete);
            Accept=itemView.findViewById(R.id.Accept);


        }
    }
}
 class updateData extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection conn = null;

        try {
            URL url;
            url = new URL(params[0]);
            conn = (HttpURLConnection) url.openConnection();
            if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
                InputStream is = conn.getInputStream();
            }else{
                InputStream err = conn.getErrorStream();
            }
            return "Done";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
}
