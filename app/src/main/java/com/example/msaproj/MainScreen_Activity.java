package com.example.msaproj;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainScreen_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView maintext, profileBt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        maintext = (TextView) findViewById(R.id.maintext);
        profileBt = (TextView) findViewById(R.id.profileBt);
        profileBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profileBt:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }

}
