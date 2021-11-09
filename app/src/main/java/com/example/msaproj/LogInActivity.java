package com.example.msaproj;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    //private static final String TAG = LogInActivity.class.getSimpleName();
    private EditText login_email, login_password;
    private Button btLogin;
    private TextView register, forgot_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = (EditText)findViewById(R.id.login_username);
        login_password = (EditText)findViewById(R.id.login_password);

        btLogin = (Button)findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
        forgot_password= (TextView) findViewById(R.id.forgotPassword);
        register= (TextView) findViewById(R.id.Register);
        register.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = login_email.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        if(email.isEmpty()){
            login_email.setError("Please provide username");
            login_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            login_email.setError("Please provide a valid email");
            login_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            login_password.setError("Please provide a password");
            login_password.requestFocus();
            return;
        }

        if(password.length() < 6){
            login_password.setError("Password must have at least 6 characters");
            login_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()) {
                        startActivity(new Intent (LogInActivity.this, MainScreen_Activity.class));
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(LogInActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(LogInActivity.this, "Your credentials are not correct!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}


