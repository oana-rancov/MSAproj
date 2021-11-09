package com.example.msaproj;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private Button signout;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        signout = (Button) findViewById(R.id.signoutbt);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LogInActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

        final TextView emailtext = (TextView) findViewById(R.id.email_title);
        final TextView emailtextinfo = (TextView) findViewById(R.id.email_info);
        final TextView firstnametext= (TextView) findViewById(R.id.firstname_title);
        final TextView firstnametextinfo= (TextView) findViewById(R.id.firstname_info);
        final TextView lastnametext = (TextView) findViewById(R.id.lastname_title);
        final TextView lastnametextinfo = (TextView) findViewById(R.id.lastname_info);

        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);
                if (userprofile != null) {
                    String email = userprofile.email;
                    String firstname = userprofile.first_name;
                    String lastname = userprofile.last_name;

                    emailtextinfo.setText(email);
                    firstnametextinfo.setText(firstname);
                    lastnametextinfo.setText(lastname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });
    }
}
