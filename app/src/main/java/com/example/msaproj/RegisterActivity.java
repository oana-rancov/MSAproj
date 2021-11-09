package com.example.msaproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    //private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText firstName, lastName, email, password;
    private Button register;
    private FirebaseAuth mAuth;
    //DatabaseReference databaseReference;
    //Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        firstName = (EditText)findViewById(R.id.registerFirstName);
        lastName = (EditText)findViewById(R.id.registerLastName);
        email = (EditText)findViewById(R.id.registerEmail);
        password = (EditText)findViewById(R.id.registerPassword);
        register = (Button)findViewById(R.id.btRegister);
        //users = new Users();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        //when we click on the Register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name= firstName.getText().toString().trim();
                String last_name=lastName.getText().toString().trim();
                String email_register=email.getText().toString().trim();
                String password_register=password.getText().toString().trim();

                if(first_name.isEmpty()) {
                    firstName.setError("First Name is required!");
                    firstName.requestFocus();
                    return;
                }
                if(last_name.isEmpty()) {
                    lastName.setError("Last Name is required!");
                    lastName.requestFocus();
                    return;
                }

                if(email_register.isEmpty()) {
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email_register).matches()) {
                   email.setError("Please provide valid email");
                   email.requestFocus();
                   return;
                }
                if(password_register.isEmpty()) {
                    password.setError("Password is required!");
                    password.requestFocus();
                    return;
                }
                if (password_register.length() <6){
                    password.setError("Password should be at least 6 characters");
                    password.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email_register, password_register)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Users user = new Users(first_name, last_name, password_register, email_register);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this, "user has been registered succesfully", Toast.LENGTH_LONG).show();

                                            }else {
                                                Toast.makeText(RegisterActivity.this, "failed to register", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                                }else {
                                    Toast.makeText(RegisterActivity.this, "failed to register", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });
    }
}