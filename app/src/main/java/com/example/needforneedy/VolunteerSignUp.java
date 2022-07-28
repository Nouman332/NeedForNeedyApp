package com.example.needforneedy;
import static com.example.needforneedy.Requestdata.KEY_Country;
import static com.example.needforneedy.volunteerdatabase.KEY_firstName;
import static com.example.needforneedy.volunteerdatabase.KEY_lastName;
import static com.example.needforneedy.volunteerdatabase.KEY_Email;
import static com.example.needforneedy.volunteerdatabase.KEY_Contact;
import static com.example.needforneedy.volunteerdatabase.KEY_Password;
import static com.example.needforneedy.volunteerdatabase.KEY_ConfirmPassword;
import static com.example.needforneedy.volunteerdatabase.KEY_weekday;
import static com.example.needforneedy.volunteerdatabase.TABLE_Name;


import androidx.annotation.NonNull;
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
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class VolunteerSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;

    TextInputLayout firstName, lastName, email, phone, Password, ConfirmPassword;
    CheckBox chkMonday, chkTuesday, chkWednesday, chkThursday, chkFriday, chkSaturday, chkSunday;
    Button btnSubmit, btnSignIn, btnonlineSubmit;
    SQLiteDatabase sq;
    volunteerdatabase db;
    String Token;
    String[] Spinner = {"Karachi", "Lahore", "Islamabad", "Rawalpindi"};
    private static final String url = "http://192.168.100.15/android_pool/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);

        setContentView(R.layout.activity_volunteer_sign_up);


        //GET ALL IDs
        spinner = (Spinner) findViewById(R.id.spinner);
        firstName = (TextInputLayout) findViewById(R.id.firstName);
        lastName = (TextInputLayout) findViewById(R.id.user);
        email = (TextInputLayout) findViewById(R.id.email);
        phone = (TextInputLayout) findViewById(R.id.phone);
        Password = (TextInputLayout) findViewById(R.id.password);
        ConfirmPassword = (TextInputLayout) findViewById(R.id.Confirmpassword);
        chkMonday = (CheckBox) findViewById(R.id.chkMonday);
        chkTuesday = (CheckBox) findViewById(R.id.chkTuesday);
        chkWednesday = (CheckBox) findViewById(R.id.chkWednesday);
        chkThursday = (CheckBox) findViewById(R.id.chkThursday);
        chkFriday = (CheckBox) findViewById(R.id.chkFriday);
        chkSaturday = (CheckBox) findViewById(R.id.chkSaturday);
        chkSunday = (CheckBox) findViewById(R.id.chkSunday);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        insertData();


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String msg = task.getResult();
                        Token = msg;
                    }
                });


        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(VolunteerSignUp.this, VolunteerActivity.class);
                startActivity(intent1);
            }
        });

        btnonlineSubmit = findViewById(R.id.btnonlineSubmit);
        btnonlineSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (false) {//!isNetworkAvailable() == true
                    new AlertDialog.Builder(VolunteerSignUp.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Internet Connection Alert")
                            .setMessage("Please Check Your Internet Connection")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }).show();

                } else if (true) {//isNetworkAvailable() == true

                    new AlertDialog.Builder(VolunteerSignUp.this)
                            .setIcon(R.drawable.ic_baseline_wifi_24)
                            .setTitle("Internet Connection Sucessfully")
                            .setMessage("Internet Connected!")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(VolunteerSignUp.this,
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
        final String fName = firstName.getEditText().getText().toString().trim();
        final String lName = lastName.getEditText().getText().toString().trim();
        final String EmailUser = email.getEditText().getText().toString().trim();
        final String phoneNo = phone.getEditText().getText().toString().trim();
        final String pass = Password.getEditText().getText().toString().trim();
        final String confirmpass = ConfirmPassword.getEditText().getText().toString().trim();
        final String SpinnerCountry = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim();


        if (!TextUtils.isEmpty(fName) && !TextUtils.isEmpty(lName) && !TextUtils.isEmpty(EmailUser) && !TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirmpass)) {

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    firstName.getEditText().setText("");
                    lastName.getEditText().setText("");
                    email.getEditText().setText("");
                    phone.getEditText().setText("");
                    Password.getEditText().setText("");
                    ConfirmPassword.getEditText().setText("");
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
                    param.put("firstName", fName);
                    param.put("lastName", lName);
                    param.put("Email", EmailUser);
                    param.put("Contact", phoneNo);
                    param.put("Password", pass);
                    param.put("ConfirmPassword", confirmpass);
                    param.put("DeviceToken", Token);


                    if (chkMonday.isChecked()) {

                        param.put("weekday", chkMonday.getText().toString());
                    }
                    if (chkTuesday.isChecked()) {
                        param.put("weekday", chkTuesday.getText().toString());
                    }
                    if (chkWednesday.isChecked()) {
                        param.put("weekday", chkWednesday.getText().toString());
                    }
                    if (chkThursday.isChecked()) {
                        param.put("weekday", chkThursday.getText().toString());
                    }
                    if (chkFriday.isChecked()) {
                        param.put("weekday", chkFriday.getText().toString());
                    }
                    if (chkSaturday.isChecked()) {
                        param.put("weekday", chkSaturday.getText().toString());
                    }
                    if (chkSunday.isChecked()) {
                        param.put("weekday", chkSunday.getText().toString());
                    }
                    param.put("Country", SpinnerCountry);

                    return param;
                }
            };


            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);


        } else {
            Toast.makeText(VolunteerSignUp.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        }
    }


    private void insertData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                String EmailTXT = email.getEditText().getText().toString().trim();
                String pass = Password.getEditText().getText().toString().trim();
                String confirmpass = ConfirmPassword.getEditText().getText().toString().trim();

                db = new volunteerdatabase(VolunteerSignUp.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_firstName, firstName.getEditText().getText().toString().trim());
                cv.put(KEY_lastName, lastName.getEditText().getText().toString().trim());
                cv.put(KEY_Email, email.getEditText().getText().toString().trim());
                cv.put(KEY_Contact, phone.getEditText().getText().toString().trim());
                cv.put(KEY_Password, Password.getEditText().getText().toString().trim());
                      if(pass.equals(confirmpass)){
       cv.put(KEY_ConfirmPassword, ConfirmPassword.getEditText().getText().toString().trim());
     }
                  else{
    Toast.makeText(VolunteerSignUp.this, "Password mismatched", Toast.LENGTH_SHORT).show();

}


                cv.put(KEY_Country, spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());



                if (chkMonday.isChecked()) {

                     cv.put(KEY_weekday, chkMonday.getText().toString());
                }
                if (chkTuesday.isChecked()) {
                    cv.put(KEY_weekday, chkTuesday.getText().toString());
                }
                if (chkWednesday.isChecked()) {
                    cv.put(KEY_weekday, chkWednesday.getText().toString());
                }
                if (chkThursday.isChecked()) {
                    cv.put(KEY_weekday, chkThursday.getText().toString());
                }
                if (chkFriday.isChecked()) {
                    cv.put(KEY_weekday, chkFriday.getText().toString());
                }
                if (chkSaturday.isChecked()) {
                    cv.put(KEY_weekday, chkSaturday.getText().toString());
                }
                if (chkSunday.isChecked()) {
                    cv.put(KEY_weekday, chkSunday.getText().toString());
                }
                Boolean checkuser = db.Checkusername(EmailTXT);
                if (checkuser == false) {

                    long result = sq.insert(TABLE_Name, null, cv);
                    if (result == -1) {

                        Toast.makeText(VolunteerSignUp.this, "   OFFLINE Data not  Inserted", Toast.LENGTH_SHORT).show();


                    } else {
                        firstName.getEditText().setText("");
                        lastName.getEditText().setText("");
                        phone.getEditText().setText("");
                        email.getEditText().setText("");
                        Password.getEditText().setText("");

                        Toast.makeText(VolunteerSignUp.this, "    OFFLINE Data Inserted", Toast.LENGTH_SHORT).show();


                    }
                }
                      else{
                        Toast.makeText(VolunteerSignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }



        });


    }

    private void validation() {

        String fName = firstName.getEditText().getText().toString().trim();
        String lName = lastName.getEditText().getText().toString().trim();
        String phoneNo = phone.getEditText().getText().toString().trim();
        String pass = Password.getEditText().getText().toString().trim();
        String confirmpass = ConfirmPassword.getEditText().getText().toString().trim();


        if(!TextUtils.isEmpty(fName) && !TextUtils.isEmpty(lName) && !TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(pass)&& !TextUtils.isEmpty(confirmpass)) {
            insertData();

        }
        else
        {
            Toast.makeText(VolunteerSignUp.this,"Enter All Fields",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



