package com.example.msaproj;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {
  private TextView title, saveBt;
  private EditText add_sum, add_category;


  @Override
    public void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_add);
      Spinner spinnerChoices=findViewById(R.id.spinner1);
      ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.choices, android.R.layout.simple_spinner_item);

      adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
      spinnerChoices.setAdapter(adapter);

      add_category = (EditText) findViewById(R.id.add_category);
      Spinner spinnerMonth=findViewById(R.id.spinner2);
      ArrayAdapter<CharSequence>adapter2=ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
      adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
      spinnerMonth.setAdapter(adapter2);

      title = (TextView) findViewById(R.id.title);
      add_sum = (EditText) findViewById(R.id.add_sum);
      saveBt = (TextView) findViewById(R.id.sabeBt);
  }

}
