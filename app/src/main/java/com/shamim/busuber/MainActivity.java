package com.shamim.busuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                sleep(4000);
            } catch (Exception e) {

            } finally {
                Intent welcome = new Intent(MainActivity.this, Login_And_Register_OptionActivity.class);
                startActivity(welcome);


            }
        }
    };
        thread.start();
}
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}