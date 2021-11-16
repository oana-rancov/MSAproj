package com.example.msaproj;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
  private TextView title, saveBt;
  private EditText add_field, category;

  @Override
    public void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_add);
      title = (TextView) findViewById(R.id.title);
      add_field = (EditText) findViewById(R.id.add_edit);
      category = (EditText) findViewById(R.id.category);
      saveBt = (TextView) findViewById(R.id.sabeBt);
  }

}
