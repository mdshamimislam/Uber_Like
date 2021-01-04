package com.shamim.busuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login_And_Register_OptionActivity extends AppCompatActivity {
    private Button exit,login, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__and__register__option);

        exit=findViewById(R.id.optionexitfromlogin_register);
        login=findViewById(R.id.optionloginfrom_login_register);
        register=findViewById(R.id.optionregisterfrom_login_register);

        startService(new Intent(Login_And_Register_OptionActivity.this,onAppKilled.class));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login_intent= new Intent(Login_And_Register_OptionActivity.this,Driver_And_Customer_Activity.class);
                login_intent.putExtra("UserType","login");
                startActivity(login_intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register_intent = new Intent(Login_And_Register_OptionActivity.this,Driver_And_Customer_Activity.class);
                register_intent.putExtra("UserType","register");
                startActivity(register_intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}