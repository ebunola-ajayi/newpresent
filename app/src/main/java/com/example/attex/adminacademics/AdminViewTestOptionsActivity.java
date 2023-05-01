package com.example.attex.adminacademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminViewTestOptionsActivity extends AppCompatActivity {

    String[] classes = {"Junior Infants", "Senior Infants", "1st Class", "2nd Class", "3rd Class", "4th Class", "5th Class", "6th Class"};
    String [] subjects = {"English", "Irish", "Maths"};
    AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapter, adapter2;

    String classSelected;
    String subjectSelected;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_test_options);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        autoCompleteTextView = findViewById(R.id.autoComplete);
        adapter = new ArrayAdapter<String>(this, R.layout.list_classes, classes);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                classSelected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AdminViewTestOptionsActivity.this, "Class: " + classSelected, Toast.LENGTH_SHORT).show();

            }
        });

        autoCompleteTextView2 = findViewById(R.id.autoComplete2);
        adapter2 = new ArrayAdapter<String>(this, R.layout.list_subjects, subjects);

        autoCompleteTextView2.setAdapter(adapter2);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                subjectSelected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AdminViewTestOptionsActivity.this, "Subject: " + subjectSelected, Toast.LENGTH_SHORT).show();

            }
        });

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminViewTestOptionsActivity.this, AdminViewTestActivity.class);
                intent.putExtra("classGrade", classSelected);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("subject", subjectSelected);
                startActivity(intent);
            }
        });


    }
}