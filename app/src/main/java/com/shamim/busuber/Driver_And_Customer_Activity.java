package com.shamim.busuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Driver_And_Customer_Activity extends AppCompatActivity {

    private String userType;
    private TextView textView;
    private String TAG="Driver_And_Register";
    private Button driver_login,driver_register,customer_login,customer_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__and__customer_);
        FindViewById();
        Intent_value_Rectrive();



        driver_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driver_login_intent = new Intent(Driver_And_Customer_Activity.this,Login_Activity.class);
                driver_login_intent.putExtra("UserType","driver_login");
                startActivity(driver_login_intent);
            }
        });

        driver_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent driver_register_intent = new Intent(Driver_And_Customer_Activity.this,Register_Activity.class);
                driver_register_intent.putExtra("UserType","driver_register");
                startActivity(driver_register_intent);
            }
        });


        customer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customer_login_intent = new Intent(Driver_And_Customer_Activity.this,Login_Activity.class);
                customer_login_intent.putExtra("UserType","customer_login");
                startActivity(customer_login_intent);
            }
        });

        customer_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent customer_register_intent = new Intent(Driver_And_Customer_Activity.this,Register_Activity.class);
                customer_register_intent.putExtra("UserType","customer_register");
                startActivity(customer_register_intent);
            }
        });





    }

    private void FindViewById()
    {
        textView= findViewById(R.id.text_from_driver_and_cus);
        driver_login= findViewById(R.id.driver_login_from_driver_and_cus);
        driver_register= findViewById(R.id.driver_register_from_driver_and_cus);
        customer_login= findViewById(R.id.customer_login_from_driver_and_cus);
        customer_register= findViewById(R.id.customer_register_from_driver_and_cus);

    }

    private void Intent_value_Rectrive() {

        Intent type =getIntent();
        userType=type.getStringExtra("UserType");

        if (userType.equals("login"))
        {
            Log.d(TAG,"userType=="+userType);
            textView.setText("Choose for Login");
            driver_register.setVisibility(View.GONE);
            customer_register.setVisibility(View.GONE);
        }
        else if (userType.equals("register"))
        {
            textView.setText("Choose for Register");
            customer_login.setVisibility(View.GONE);
            driver_login.setVisibility(View.GONE);
        }

    }
}