package com.example.needforneedy;

import static com.example.needforneedy.Requestdata.KEY_Country;
import static com.example.needforneedy.Requestdata.KEY_FULL_ADDRESS;
import static com.example.needforneedy.Requestdata.KEY_MOBILE_NUMBER;
import static com.example.needforneedy.Requestdata.KEY_Name;
import static com.example.needforneedy.Requestdata.KEY_foodType;
import static com.example.needforneedy.Requestdata.KEY_Current_Location;
import static com.example.needforneedy.Requestdata.TABLE_Name;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RequestFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    TextInputLayout Name, MOBILE_NUMBER, FULL_ADDRESS, foodType, ID;
    Button btnLocation, btnSubmit,btnonlineSubmit;
    Requestdata db;
    SQLiteDatabase sq;
    TextView txtLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static final String url = "http://192.168.100.15/android_pool/RequestData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);

        setContentView(R.layout.activity_request_food);

        spinner = findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        insertData();
        spinner.setOnItemSelectedListener(this);

        String[] Spinner = {"Karachi", "Lahore", "Islamabad", "Rawalpindi"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        txtLocation = findViewById(R.id.txtLocation);
        btnonlineSubmit = findViewById(R.id.btnonlineSubmit);
        btnonlineSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isNetworkAvailable()==true)
                {
                    new AlertDialog.Builder(RequestFood.this)
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

                    new AlertDialog.Builder(RequestFood.this)
                            .setIcon(R.drawable.ic_baseline_wifi_24)
                            .setTitle("Internet Connection Sucessfully")
                            .setMessage("Internet Connected!")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(RequestFood.this,
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


        Name = (TextInputLayout) findViewById(R.id.Name);
        //ID = (TextInputLayout) findViewById(R.id.ID);
        MOBILE_NUMBER = (TextInputLayout) findViewById(R.id.MOBILE_NUMBER);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        FULL_ADDRESS = (TextInputLayout) findViewById(R.id.FULL_ADDRESS);
        foodType = (TextInputLayout) findViewById(R.id.foodType);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(RequestFood.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    GetLocation();
                } else {
                    ActivityCompat.requestPermissions(RequestFood.this, new String[]
                            {
                                    Manifest.permission.ACCESS_FINE_LOCATION
                            }, 44);

                }

            }
        });

    }

    private void OnLineInsertdata() {
        final String uname = Name.getEditText().getText().toString().trim();
        final String mobile = MOBILE_NUMBER.getEditText().getText().toString().trim();
        final String address = FULL_ADDRESS.getEditText().getText().toString().trim();
        final String foodtype =foodType.getEditText().getText().toString().trim();
        final String Currentlocation = txtLocation.getText().toString();
        final String SpinnerPerson = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim();


        if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(foodtype)) {

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Name.getEditText().setText("");
                    MOBILE_NUMBER.getEditText().setText("");
                    FULL_ADDRESS.getEditText().setText("");
                    foodType.getEditText().setText("");
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
                    param.put("typeOfFood", foodtype);
                    param.put("Person", SpinnerPerson);
                    param.put("Current_Location",Currentlocation);
                    return param;
                }
            };


            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);


        } else {
            Toast.makeText(RequestFood.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("MissingPermission")
    private void GetLocation() {

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {


                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(RequestFood.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);

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

                db = new Requestdata(RequestFood.this);
                String NameTXT = Name.getEditText().getText().toString().trim();
                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_Name, Name.getEditText().getText().toString().trim());
                cv.put(KEY_MOBILE_NUMBER, MOBILE_NUMBER.getEditText().getText().toString().trim());
                cv.put(KEY_FULL_ADDRESS, FULL_ADDRESS.getEditText().getText().toString().trim());
                cv.put(KEY_foodType, foodType.getEditText().getText().toString().trim());
                cv.put(
                        KEY_Current_Location, txtLocation.getText().toString().trim());

                cv.put(KEY_Country,spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());

                Boolean checkuser = db.Checkname(NameTXT);
                if (checkuser == false) {

                    long result = sq.insert(Donatefood.TABLE_Name, null, cv);
                    if (result == -1) {

                        Toast.makeText(RequestFood.this, "  OFFLINE Data not Inserted", Toast.LENGTH_SHORT).show();


                    } else {
                        Name.getEditText().setText("");
                        MOBILE_NUMBER.getEditText().setText("");
                        FULL_ADDRESS.getEditText().setText("");
                        foodType.getEditText().setText("");
                        Toast.makeText(RequestFood.this, " OFFLINE Data Inserted", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(RequestFood.this, "UserName Already exist! Enter the correct Name ", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void validation() {
            String uname = Name.getEditText().getText().toString().trim();
            String mobile = MOBILE_NUMBER.getEditText().getText().toString().trim();
            String address = FULL_ADDRESS.getEditText().getText().toString().trim();
            String food_Type = foodType.getEditText().getText().toString().trim();

            if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(food_Type)) {
               insertData();
                } else {
                Toast.makeText(RequestFood.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



