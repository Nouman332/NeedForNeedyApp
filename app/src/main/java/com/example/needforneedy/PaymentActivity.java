package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    AutoCompleteTextView autoPayment, autoCardType;
    Button btnPay;
    EditText etVCode, etCardNo;

    String[] arrPayment = { "Payment Card"};
    String[] arrCardType = { "Visa", "Master Card"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);

        autoPayment = findViewById(R.id.autoPayment);
        autoCardType = findViewById(R.id.autoCardType);
        etVCode = findViewById(R.id.etVCode);
        etCardNo = findViewById(R.id.etCardNo);

        //set autoCompleteTextView values for autoPayment
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, arrPayment);

        autoPayment.setThreshold(1);//user must enter minimum 2 char to get auto values;
        autoPayment.setAdapter(adapter1);

        //set autoCompleteTextView values for autoPayment
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, arrCardType);

        autoCardType.setThreshold(1);
        autoCardType.setAdapter(adapter2);



        btnPay = findViewById(R.id.btnPay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });

    }

    private void validateFields() {
        String Payment = autoPayment.getText().toString();
        String CardType = autoCardType.getText().toString();
        String VerificationCode = etVCode.getText().toString();
        String CardNo = etCardNo.getText().toString();

        if(!TextUtils.isEmpty(Payment) && !TextUtils.isEmpty(CardType) && !TextUtils.isEmpty(VerificationCode) && !TextUtils.isEmpty(CardNo))
        {
//            if(CardNo.length() != 4)
//            {
//                Toast.makeText(PaymentActivity.this,"Enter Correct Card Number!",Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
                Intent intent1 = new Intent(PaymentActivity.this, PaymentSucessful.class);
                startActivity(intent1);
                clear();
//            }
        }
        else
        {
            Toast.makeText(PaymentActivity.this,"Enter All the Fields",Toast.LENGTH_SHORT).show();
        }
    }

    private void clear() {

        autoPayment.setText("");
        autoCardType.setText("");
        etVCode.setText("");
        etCardNo.setText("");
    }
}