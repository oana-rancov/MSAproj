package com.example.msaproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
  private static final String TAG = AddActivity.class.getSimpleName();
  private TextView title;
  Button saveBt;
  private EditText add_sum, add_type;
  Spinner spinnerMonths;
  Spinner spinnerCategory;
  String textMonthSelected, textCategorySelecetd;

  FirebaseDatabase firebaseDatabase;
  DatabaseReference databaseReference;
  IncomesExpenses incomesexpenses;

  @Override
    public void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "Created!!!!!");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);
    add_type = (EditText) findViewById(R.id.selection_type);


    title = (TextView) findViewById(R.id.title);
    add_sum = (EditText) findViewById(R.id.add_sum);
    saveBt = (Button) findViewById(R.id.saveBt);
    spinnerMonths = (Spinner) findViewById(R.id.spinnerMonths);
    spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);

    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("Incomes/Expenses");
    incomesexpenses = new IncomesExpenses();

    //Months
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinnerMonths.setAdapter(adapter);

    //Category
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapterCateg = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    adapterCateg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinnerCategory.setAdapter(adapterCateg);

    spinnerMonths.setOnItemSelectedListener(this);
    spinnerCategory.setOnItemSelectedListener(this);

    saveBt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String sum= add_sum.getText().toString().trim();
        String type = add_type.getText().toString().trim();

      //insert drop down list

        if(sum.isEmpty()) {
          add_sum.setError("Please enter the sum");
          add_sum.requestFocus();
          return;
        }


        if(type.isEmpty()) {
          add_type.setError("Please provide the type");
          add_type.requestFocus();

        }



        //rootNode = FirebaseDatabase.getInstance();
       // reference = rootNode.getReference("months");
        //reference = rootNode.getReference(String.valueOf(spinnerMonth));
        //reference.setValue("first entry");
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        addDatatoFirebase(currentuser, textMonthSelected, sum, textCategorySelecetd, type);
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

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    if(adapterView.getId() == R.id.spinnerMonths){
      textMonthSelected = spinnerMonths.getSelectedItem().toString();
    }
    else if(adapterView.getId() == R.id.spinnerCategory)
      textCategorySelecetd = spinnerCategory.getSelectedItem().toString();
  }

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {

  }
}

