package com.example.attex.parentacademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.attex.R;

public class ChooseSubjectActivity extends AppCompatActivity {

    String[] subjects = {"English", "Irish", "Maths"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String studentID = i.getStringExtra("studentID");
        String classID = i.getStringExtra("classID");
        String classGrade = i.getStringExtra("classGrade");

        autoCompleteTextView = findViewById(R.id.autoComplete);
        adapter = new ArrayAdapter<String>(this, R.layout.list_subjects, subjects);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String subject = adapterView.getItemAtPosition(i).toString();

                Intent intent = new Intent(ChooseSubjectActivity.this, ParentViewStandardResultActivity.class);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

    }
}