package com.example.needforneedy;

import static com.example.needforneedy.NGOdatabase.KEY_NGOName;
import static com.example.needforneedy.NGOdatabase.KEY_Website;
import static com.example.needforneedy.NGOdatabase.KEY_Email;
import static com.example.needforneedy.NGOdatabase.KEY_Phone;
import static com.example.needforneedy.NGOdatabase.KEY_Address;
import static com.example.needforneedy.NGOdatabase.TABLE_Name;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class NGO_Registration extends AppCompatActivity {

    TextInputLayout NGO_name,etWebsite,email,phone,etAddress;
    Button btnSubmit,btnonlineSubmit;
    NGOdatabase db;
    SQLiteDatabase sq;

    private static final String url = "http://192.168.100.15/android_pool/NGOData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_registration);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);
        NGO_name = findViewById(R.id.NGO_Name);
        etWebsite = findViewById(R.id.etWebsite);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        etAddress = findViewById(R.id.etAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnonlineSubmit=findViewById(R.id.btnonlineSubmit);

        insertData();


        btnonlineSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isNetworkAvailable()==true)
                {
                    new AlertDialog.Builder(NGO_Registration.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Internet Connection Alert")
                            .setMessage("Please Check Your Internet Connection")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }).show();

                }
                else if(isNetworkAvailable()==true)
                {

                    new AlertDialog.Builder(NGO_Registration.this)
                            .setIcon(R.drawable.ic_baseline_wifi_24)
                            .setTitle("Internet Connection Sucessfully")
                            .setMessage("Internet Connected!")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(NGO_Registration.this,
                                            "Internet Connected", Toast.LENGTH_LONG).show();
                                    OnLineInsertdata();
                                }
                            }).show();

                }

            }

            public boolean isNetworkAvailable() {

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                if (connectivityManager != null) {


                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                        if (capabilities != null) {
                            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                                return true;
                            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                                return true;
                            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                                return true;
                            }
                        }
                    }
                }

                return false;

            }

        });


    }

    private void OnLineInsertdata() {

        final String ngo_name = NGO_name.getEditText().getText().toString().trim();
        final String website = etWebsite.getEditText().getText().toString().trim();
        final String EmailUSER = email.getEditText().getText().toString().trim();
        final String phone_no = phone.getEditText().getText().toString().trim();
        final String address = etAddress.getEditText().getText().toString().trim();



        if (!TextUtils.isEmpty(ngo_name) && !TextUtils.isEmpty(website) && !TextUtils.isEmpty(EmailUSER) && !TextUtils.isEmpty(phone_no)&& !TextUtils.isEmpty(address)) {

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                 NGO_name.getEditText().setText("");
                   etWebsite.getEditText().setText("");
                    email.getEditText().setText("");
                   phone.getEditText().setText("");
                    etAddress.getEditText().setText("");
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("NGOName", ngo_name);
                    param.put("Website", website);
                    param.put("Email", EmailUSER);
                    param.put("Phone", phone_no);
                    param.put("Address", address);

                    return param;
                }
            };


            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);


        } else {
            Toast.makeText(NGO_Registration.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validateFields();
                String EmailTXT = email.getEditText().getText().toString().trim();

                db = new NGOdatabase(NGO_Registration.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_NGOName,NGO_name.getEditText().getText().toString().trim());
                cv.put(KEY_Website,etWebsite.getEditText().getText().toString().trim());
                cv.put(KEY_Email,email.getEditText().getText().toString().trim());
                cv.put(KEY_Phone,phone.getEditText().getText().toString().trim());
                cv.put(KEY_Address, etAddress.getEditText().getText().toString().trim());

                Boolean checkuser = db.Checkusername(EmailTXT);
                if (checkuser == false) {

                    long result = sq.insert(TABLE_Name, null, cv);
                    if(result == -1) {

                        Toast.makeText(NGO_Registration.this, "  Data not Inserted", Toast.LENGTH_SHORT).show();


                    } else {
                        NGO_name.getEditText().setText("");
                        etWebsite.getEditText().setText("");
                        email.getEditText().setText("");
                        phone.getEditText().setText("");
                        etAddress.getEditText().setText("");
                        Toast.makeText(NGO_Registration.this, "    Data Inserted", Toast.LENGTH_SHORT).show();


                    }
                }else{
                    Toast.makeText(NGO_Registration.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                }

            }


        });




    }



    private void validateFields() {
        String NGO = NGO_name.getEditText().getText().toString();
        String Website = etWebsite.getEditText().getText().toString();
        String Email = email.getEditText().getText().toString();
        String Phone = phone.getEditText().getText().toString();
        String Address = etAddress.getEditText().getText().toString();

        if(!TextUtils.isEmpty(NGO) && !TextUtils.isEmpty(Website) && !TextUtils.isEmpty(Email)
                && !TextUtils.isEmpty(Phone) && !TextUtils.isEmpty(Address)
        )
        {
           insertData();

        }
        else
        {
            Toast.makeText(NGO_Registration.this,"Enter All the Fields",Toast.LENGTH_SHORT).show();
        }

    }


}