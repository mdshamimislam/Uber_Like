package com.shamim.busuber;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private String userType;
    private TextView textView;
    private FirebaseAuth mAuth;
    private EditText email,password;
    private Button driver_login,customer_login,cancel,driver_registerBtn,customer_registerBtn;
    private ProgressDialog loadBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        FindViewById();
        Intent_value_Rectrive();
        loadBar= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();


        driver_login.setOnClickListener(this);
        customer_login.setOnClickListener(this);
        cancel.setOnClickListener(this);
        driver_registerBtn.setOnClickListener(this);
        customer_registerBtn.setOnClickListener(this);

    }
    private void FindViewById()
    {
        textView= findViewById(R.id.textView_from_login);
        driver_login= findViewById(R.id.driver_loginbtn_from_login);
        customer_login= findViewById(R.id.customer_loginbtn_from_login);
        cancel= findViewById(R.id.cancel_from_login);
        driver_registerBtn=findViewById(R.id.go_driverregister_from_login);
        email=findViewById(R.id.email_from_login);
        password= findViewById(R.id.password_from_login);
        customer_registerBtn= findViewById(R.id.go_customerregister_from_login);
    }

    private void Intent_value_Rectrive() {
        Intent type =getIntent();

        userType=type.getStringExtra("UserType");

        if (userType.equals("customer_login"))
        {
            textView.setText("Customer Login Page");
            driver_login.setVisibility(View.GONE);
            driver_registerBtn.setVisibility(View.GONE);

        }

        else  if (userType.equals("driver_login"))
        {
            textView.setText("Driver Login Page");
            customer_login.setVisibility(View.GONE);
            customer_registerBtn.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        final String Email = email.getText().toString();
       final String Password=password.getText().toString();
        switch (v.getId())
        {
            case R.id.driver_loginbtn_from_login:
                if (Email.isEmpty() && Password.isEmpty())
                {
                    Toast.makeText(this, "Please Enter Email And Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    loadBar.setTitle("Driver Login");
                    loadBar.setMessage("Please wait>>>>>>>");
                    loadBar.show();
                    mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login_Activity.this, "SignIn error", Toast.LENGTH_SHORT).show();
                                loadBar.dismiss();
                            }
                            else
                            {

                                Toast.makeText(Login_Activity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                                loadBar.dismiss();
                                startActivity(new Intent(Login_Activity.this,Driver_MapsActivity.class));

                            }
                        }
                    });
                }
                break;




                case R.id.customer_loginbtn_from_login:
                if (Email.isEmpty() && Password.isEmpty())
                {
                    Toast.makeText(this, "Please Enter Email And Password", Toast.LENGTH_SHORT).show();
                }
                else {

                    loadBar.setTitle("Customer Login");
                    loadBar.setMessage("Please wait>>>>>>>");
                    loadBar.show();
                    mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login_Activity.this, "SignIn error", Toast.LENGTH_SHORT).show();
                                loadBar.dismiss();
                            }
                            else
                            {
                                Toast.makeText(Login_Activity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                                loadBar.dismiss();
                                startActivity(new Intent(Login_Activity.this,Customer_Map_Activity.class));

                            }
                        }
                    });
                }

                break;
            case R.id.cancel_from_login:
                startActivity(new Intent(Login_Activity.this,Login_And_Register_OptionActivity.class ));

                break;
            case R.id.go_driverregister_from_login:

                Intent go_driverregisterIntent = new Intent(Login_Activity.this,Register_Activity.class);
                go_driverregisterIntent.putExtra("UserType","driver_register");
                startActivity(go_driverregisterIntent);
                break;

            case R.id.go_customerregister_from_login:

                Intent go_customerregisterIntent = new Intent(Login_Activity.this,Register_Activity.class);
                go_customerregisterIntent.putExtra("UserType","customer_register");
                startActivity(go_customerregisterIntent);

        }

    }


}