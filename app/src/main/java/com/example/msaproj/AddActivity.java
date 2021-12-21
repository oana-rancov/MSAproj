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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddActivity extends AppCompatActivity {
  private TextView title;
  Button saveBt;
  private EditText add_sum, add_category, add_type, add_month;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference databaseReference;
  IncomesExpenses incomesexpenses;

  @Override
    public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);
    add_type = (EditText) findViewById(R.id.selection_type);

    add_category = (EditText) findViewById(R.id.add_category);
    add_month = (EditText) findViewById(R.id.month);

    title = (TextView) findViewById(R.id.title);
    add_sum = (EditText) findViewById(R.id.add_sum);
    saveBt = (Button) findViewById(R.id.saveBt);

    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("Incomes/Expenses");
    incomesexpenses = new IncomesExpenses();

    saveBt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String sum= add_sum.getText().toString().trim();
        String category=add_category.getText().toString().trim();
        String type = add_type.getText().toString().trim();
        String month =add_month.getText().toString().trim();

      //insert drop down list

        if(sum.isEmpty()) {
          add_sum.setError("Please enter the sum");
          add_sum.requestFocus();
          return;
        }

        if(category.isEmpty()) {
          add_category.setError("Please type the category");
          add_category.requestFocus();

        }

        if(type.isEmpty()) {
          add_type.setError("Please provide the type");
          add_type.requestFocus();

        }

        if(month.isEmpty()) {
          add_month.setError("Please provide the month");
          add_month.requestFocus();

        }

        //rootNode = FirebaseDatabase.getInstance();
       // reference = rootNode.getReference("months");
        //reference = rootNode.getReference(String.valueOf(spinnerMonth));
        //reference.setValue("first entry");
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        addDatatoFirebase(currentuser, month, sum, category, type);
      }


    });
  }

  private void addDatatoFirebase(String currentuser, String month, String sum, String category, String type) {
    incomesexpenses.setUser_uid(currentuser);
    incomesexpenses.setMonth(month);
    incomesexpenses.setSum(sum);
    incomesexpenses.setCategory(category);
    incomesexpenses.setType(type);

    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        databaseReference.setValue(incomesexpenses);
        Toast.makeText(AddActivity.this, "data added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent (AddActivity.this, NavigationActivity.class));
      }

      @Override
      public void onCancelled(@NonNull  DatabaseError error) {
        Toast.makeText(AddActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
      }
    });
  }
  }

