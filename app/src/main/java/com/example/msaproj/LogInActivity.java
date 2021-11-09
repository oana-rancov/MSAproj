package com.example.msaproj;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    //private static final String TAG = LogInActivity.class.getSimpleName();
    private EditText login_username, login_password;
    private Button btLogin;
    private TextView register, forgot_password;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = (EditText)findViewById(R.id.login_username);
        login_password = (EditText)findViewById(R.id.login_password);

        btLogin = (Button)findViewById(R.id.btLogin);
        forgot_password= (TextView) findViewById(R.id.forgotPassword);
        register= (TextView) findViewById(R.id.Register);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}


