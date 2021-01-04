package com.shamim.busuber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private EditText email,password;
    private Button driver_submit,customer_submit,driverlogin,customerlogin;
    private String userType;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private String TAG="Register_Activity";
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FindViewById();
        Intent_value_Rectrive();
        loadingBar= new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        //what is the wwork of this
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(Register_Activity.this, Login_And_Register_OptionActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        driver_submit.setOnClickListener(this);
        customer_submit.setOnClickListener(this);
        driverlogin.setOnClickListener(this);
        customerlogin.setOnClickListener(this);
    }

    private void FindViewById()
    {
        textView=findViewById(R.id.textview_from_register_activity);
        email=findViewById(R.id.email_from_register_activity);
        password=findViewById(R.id.password_from_register_activity);
        driver_submit=findViewById(R.id.driver_signup_from_register_activity);
        customer_submit=findViewById(R.id.customer_signup_from_register_activity);
        driverlogin=findViewById(R.id.go_driverlogin_from_register_activity);
        customerlogin=findViewById(R.id.go_customer_login_from_register_activity);
    }

    private void Intent_value_Rectrive()
    {
        Intent type =getIntent();
        userType=type.getStringExtra("UserType");

        if (userType.equals("driver_register"))
        {
            textView.setText("Driver Register Page");
            customer_submit.setVisibility(View.GONE);
            customerlogin.setVisibility(View.GONE);
        }
        if (userType.equals("customer_register"))
        {
            textView.setText("Customer Register Page");
            driver_submit.setVisibility(View.GONE);
            driverlogin.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        final String Email = email.getText().toString();
        final String Password = password.getText().toString();
        switch (v.getId()) {
            case R.id.customer_signup_from_register_activity:
                if (Email.isEmpty() && Password.isEmpty()) {
                    Toast.makeText(this, "Please Enter Email And Password", Toast.LENGTH_SHORT).show();
                } else {
                    loadingBar.setTitle("Customer Register");
                    loadingBar.setMessage("Please wait...");
                    loadingBar.show();
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(Register_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "  Task==" + task);
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register_Activity.this, "sign up error", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {
                                String user_id = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(user_id).child("name");
                                ;
                                current_user_db.setValue("name");
                                loadingBar.dismiss();
                                Toast.makeText(Register_Activity.this, "Successfully Customer Register", Toast.LENGTH_SHORT).show();
                                Intent customer_intent = new Intent(Register_Activity.this, Login_Activity.class);
                                customer_intent.putExtra("UserType", "customer_login");
                                startActivity(customer_intent);
                                finish();

                            }
                        }
                    });

                }
                break;

            case R.id.driver_signup_from_register_activity:
                if (Email.isEmpty() && Password.isEmpty()) {
                    Toast.makeText(this, "Please Enter Email And Password", Toast.LENGTH_SHORT).show();
                } else {
                    loadingBar.setTitle("Driver Registration");
                    loadingBar.setMessage("Please wait...");
                    loadingBar.show();
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(Register_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register_Activity.this, "sign up error", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                            } else {

                                String user_id = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id).child("name");
                                current_user_db.setValue(Email);
                                loadingBar.dismiss();
                                Toast.makeText(Register_Activity.this, "Successfully Driver Registration", Toast.LENGTH_SHORT).show();
                                Intent driver_intent = new Intent(Register_Activity.this, Login_Activity.class);
                                driver_intent.putExtra("UserType", "driver_login");
                                startActivity(driver_intent);
                                finish();

                            }


                        }
                    });

                }

                break;

            case R.id.go_driverlogin_from_register_activity:
                Intent driverintent = new Intent(Register_Activity.this, Login_Activity.class);
                driverintent.putExtra("UserType", "driver_login");
                startActivity(driverintent);
                finish();
                break;

            case R.id.go_customer_login_from_register_activity:
                Intent customerintent = new Intent(Register_Activity.this, Login_Activity.class);
                customerintent.putExtra("UserType", "customer_login");
                startActivity(customerintent);
                finish();
                break;
        }
    }
}