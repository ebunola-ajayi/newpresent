package com.example.attex.adminacademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminTestDetailsActivity extends AppCompatActivity {

    EditText classgradeET, subjectET, yearET;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_test_details);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        classgradeET = findViewById(R.id.classgrade);
        subjectET = findViewById(R.id.subject);
        yearET = findViewById(R.id.year);

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = classgradeET.getText().toString();
                String subject = subjectET.getText().toString();
                String year = yearET.getText().toString();

                if(!subject.equals("English") || !subject.equals("Maths") || !subject.equals("Irish")){
                    Toast.makeText(AdminTestDetailsActivity.this, "Standard Exam Subjects Options Are", Toast.LENGTH_SHORT).show();
                    subjectET.requestFocus();
                }

                Intent intent = new Intent(AdminTestDetailsActivity.this, AdminCreateTestActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("subject", subject);
                intent.putExtra("year", year);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });
    }
}