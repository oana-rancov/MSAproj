package com.example.msaproj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;





public class AddActivity extends AppCompatActivity {
  private TextView title;
  Button saveBt;
  private EditText add_sum, add_category;
  FirebaseDatabase rootNode;
  DatabaseReference reference;

  @Override
    public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);
    Spinner spinnerChoices = findViewById(R.id.spinner1);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choices, android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
    spinnerChoices.setAdapter(adapter);

    add_category = (EditText) findViewById(R.id.add_category);
    Spinner spinnerMonth = findViewById(R.id.spinner2);
    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
    spinnerMonth.setAdapter(adapter2);

    title = (TextView) findViewById(R.id.title);
    add_sum = (EditText) findViewById(R.id.add_sum);
    saveBt = (Button) findViewById(R.id.saveBt);


    saveBt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String sum= add_sum.getText().toString().trim();
        String category=add_category.getText().toString().trim();

        if(sum.isEmpty()) {
          add_sum.setError("Please enter the sum");
          add_sum.requestFocus();
          return;
        }

        if(category.isEmpty()) {
          add_category.setError("Category is mandatory");
          add_category.requestFocus();
          return;
        }

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("months");
        reference.setValue("first entry");

      }
    });
  }
  }

