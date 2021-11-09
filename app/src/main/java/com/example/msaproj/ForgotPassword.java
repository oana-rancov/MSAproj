package com.example.msaproj;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private TextView resetpass;
    private EditText enteremail;
    private Button resetbt;

    FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        resetpass = (TextView) findViewById(R.id.resetpass);
        enteremail = (EditText) findViewById(R.id.enteremail);
        resetbt = (Button) findViewById(R.id.resetbt);

        auth = FirebaseAuth.getInstance();

        resetbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }
    private void resetPassword() {
        String email = enteremail.getText().toString().trim();
        if (email.isEmpty()) {
            enteremail.setError("Please provide email");
            enteremail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            enteremail.setError("Please provide valid email");
            enteremail.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ForgotPassword.this, "Something went wromg! Try Again!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
