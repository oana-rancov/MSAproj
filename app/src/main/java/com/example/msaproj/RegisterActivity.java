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

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText firstName, lastName, userName, email, password;
    private Button register;
    DatabaseReference databaseReference;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = (EditText)findViewById(R.id.registerFirstName);
        lastName = (EditText)findViewById(R.id.registerLastName);
        userName = (EditText)findViewById(R.id.registerUsername);
        email = (EditText)findViewById(R.id.registerEmail);
        password = (EditText)findViewById(R.id.registerPassword);
        register = (Button)findViewById(R.id.btRegister);
        users = new Users();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        //when we click on the Register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.setFirst_name(firstName.getText().toString().trim());
                users.setLast_name(lastName.getText().toString().trim());
                users.setUsername(userName.getText().toString().trim());
                users.setEmail(email.getText().toString().trim());
                users.setHash_pass(password.getText().toString().trim());
                Log.d(TAG, "FirstName = " + firstName.getText() + "\nLast Name = " + lastName.getText() + "\nPass = " + password.getText());

                databaseReference.push().setValue(users);

                Toast.makeText(RegisterActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}