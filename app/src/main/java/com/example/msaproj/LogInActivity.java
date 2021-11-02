package com.example.msaproj;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity{
    private static final String TAG = LogInActivity.class.getSimpleName();
    private EditText login_username, login_password;
    private Button btLogin, btForgotPass;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = (EditText)findViewById(R.id.login_username);
        login_password = (EditText)findViewById(R.id.login_password);

        btLogin = (Button)findViewById(R.id.btLogin);
        btForgotPass = (Button)findViewById(R.id.btForgotPass);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        //when we click on the Register button
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //

            }
        });
    }
}


