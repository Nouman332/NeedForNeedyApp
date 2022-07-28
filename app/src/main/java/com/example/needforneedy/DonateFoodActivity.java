package com.example.needforneedy;

import static com.example.needforneedy.Donatefood.KEY_Current_Location;

import static com.example.needforneedy.Donatefood.KEY_FULL_ADDRESS;
//import static com.example.mainsemester.Donatefood.KEY_ID;
import static com.example.needforneedy.Donatefood.KEY_MOBILE_NUMBER;
import static com.example.needforneedy.Donatefood.KEY_Name;
import static com.example.needforneedy.Donatefood.KEY_Person;
import static com.example.needforneedy.Donatefood.KEY_foodDrop;
import static com.example.needforneedy.Donatefood.KEY_foodType;
import static com.example.needforneedy.Donatefood.TABLE_Name;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.needforneedy.DonateFetchdta;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DonateFoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;

    RadioGroup radioGroup;
    RadioButton radioDropFood, radioPickUp;

    TextInputLayout Name, MOBILE_NUMBER, FULL_ADDRESS, typeOfFood, ID;
    Button btnLocation, btnSubmit,btnonlineSubmit;
    TextView txtLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    Donatefood db;
    SQLiteDatabase sq;

    String[] Spinner = {"1person", "2person", "3person", "4person", "5person", "10person", "15person", "20person", "100+ person"};

    private static final String url = "http://192.168.100.15/android_pool/DonorData.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        setContentView(R.layout.activity_donate_food);
        findid();
        insertData();

        btnonlineSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        if(false)
        {//!isNetworkAvailable()==true
            new AlertDialog.Builder(DonateFoodActivity.this)
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
        else if(true)
        { //isNetworkAvailable()==true

            new AlertDialog.Builder(DonateFoodActivity.this)
                    .setIcon(R.drawable.ic_baseline_wifi_24)
                    .setTitle("Internet Connection Sucessfully")
                    .setMessage("Internet Connected!")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(DonateFoodActivity.this,
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
        final String uname = Name.getEditText().getText().toString().trim();
        final String mobile = MOBILE_NUMBER.getEditText().getText().toString().trim();
        final String address = FULL_ADDRESS.getEditText().getText().toString().trim();
        final String foodType = typeOfFood.getEditText().getText().toString().trim();
        final String rdDropFood = radioDropFood.getText().toString();
        final String rdPickUp = radioPickUp.getText().toString();
        final String Currentlocation = txtLocation.getText().toString();
        final String SpinnerPerson = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim();


        if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(foodType)) {

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Name.getEditText().setText("");
                    MOBILE_NUMBER.getEditText().setText("");
                    FULL_ADDRESS.getEditText().setText("");
                    typeOfFood.getEditText().setText("");
                    txtLocation.setText("");
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
                    param.put("Name", uname);
                    param.put("MOBILE_NUMBER", mobile);
                    param.put("FULL_ADDRESS", address);
                    param.put("typeOfFood", foodType);
                    if (radioDropFood.isChecked()) {
                        param.put("foodDrop", rdDropFood);
                    }
                    if (radioPickUp.isChecked()) {
                        param.put("foodDrop", rdPickUp);
                    }

                    param.put("Person", SpinnerPerson);
                    param.put("Current_Location",Currentlocation);
                    return param;
                }
            };


            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);


        } else {
            Toast.makeText(DonateFoodActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        }
    }




    private void findid() {

        radioGroup = findViewById(R.id.RadioGroup);
        txtLocation = findViewById(R.id.txtLocation);
        btnonlineSubmit = findViewById(R.id.btnonlineSubmit);
        radioDropFood = (RadioButton) findViewById(R.id.radioDropFood);
        spinner = findViewById(R.id.spinner);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        radioPickUp = (RadioButton) findViewById(R.id.radioPickUp);
        Name = (TextInputLayout) findViewById(R.id.Name);
        //ID = (TextInputLayout) findViewById(R.id.ID);
        MOBILE_NUMBER = (TextInputLayout) findViewById(R.id.MOBILE_NUMBER);
        FULL_ADDRESS = (TextInputLayout) findViewById(R.id.FULL_ADDRESS);
        typeOfFood = (TextInputLayout) findViewById(R.id.typeOfFood);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);



        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Intent intent = new Intent(DonateFoodActivity.this,MapActivity.class);
                //
                //  startActivity(intent);
                if (ActivityCompat.checkSelfPermission(DonateFoodActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(DonateFoodActivity.this, new String[]
                            {
                                    Manifest.permission.ACCESS_FINE_LOCATION
                            }, 44);

                }

            }
        });

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {


                Location location = task.getResult();
                if(location != null){
                    Geocoder geocoder = new Geocoder(DonateFoodActivity.this,Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(),1);

                      String address = addresses.get(0).getAddressLine(0);
                      txtLocation.setText(address);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }

    private void insertData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validation();
                String NameTXT = Name.getEditText().getText().toString().trim();
                db = new Donatefood(DonateFoodActivity.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_Name, Name.getEditText().getText().toString().trim());
                cv.put(KEY_MOBILE_NUMBER, MOBILE_NUMBER.getEditText().getText().toString().trim());
                cv.put(KEY_FULL_ADDRESS, FULL_ADDRESS.getEditText().getText().toString().trim());
                cv.put(KEY_foodType, typeOfFood.getEditText().getText().toString().trim());

               cv.put(KEY_Current_Location, txtLocation.getText().toString().trim());

                //  cv.put(KEY_ID, ID.getEditText().getText().toString().trim());

                if(radioDropFood.isChecked()) {
                    cv.put(KEY_foodDrop,radioDropFood.getText().toString().trim());
                }
                if(radioPickUp.isChecked()) {
                    cv.put(KEY_foodDrop,radioPickUp.getText().toString().trim());
                }
                //for array
                cv.put(KEY_Person,spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());

                Boolean checkuser = db.Checkname(NameTXT);
                if (checkuser == false) {

                    long result = sq.insert(Donatefood.TABLE_Name, null, cv);
                    if (result == -1) {

                        Toast.makeText(DonateFoodActivity.this, "  OFFLINE Data not Inserted", Toast.LENGTH_SHORT).show();


                    } else {
                        Name.getEditText().setText("");
                        MOBILE_NUMBER.getEditText().setText("");
                        FULL_ADDRESS.getEditText().setText("");
                        typeOfFood.getEditText().setText("");
                        Toast.makeText(DonateFoodActivity.this, "    OFFLINE Data Inserted", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(DonateFoodActivity.this, "UserName Already exist! Enter the correct Name ", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }
    private void validation() {
        String uname = Name.getEditText().getText().toString().trim();
        String mobile = MOBILE_NUMBER.getEditText().getText().toString().trim();
        String address = FULL_ADDRESS.getEditText().getText().toString().trim();
        String foodType = typeOfFood.getEditText().getText().toString().trim();
        String rdDropFood = radioDropFood.getText().toString();
        String rdPickUp = radioPickUp.getText().toString();
        if(!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(foodType)) {
            insertData();
        }
        else
        {
            Toast.makeText(DonateFoodActivity.this,"Enter All Fields",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}