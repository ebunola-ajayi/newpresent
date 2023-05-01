package com.example.attex.teacheracademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TeacherStandardRecordActivity extends AppCompatActivity {

    TextView studentName;
    EditText noteET, gradeET;
    Button record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_standard_record);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String studentID = i.getStringExtra("studentID");
        String subject = i.getStringExtra("subject");
        String classID = i.getStringExtra("classID");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String firstName = i.getStringExtra("firstName");
        String lastName = i.getStringExtra("lastName");

        studentName = findViewById(R.id.studentName);
        studentName.setText(firstName + " " + lastName);


        noteET = findViewById(R.id.noteET);
        gradeET = findViewById(R.id.gradeET);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("StandardExamResults").child(schoolID).child(classGrade).child(classID).child(subject).child(studentID);


        record = findViewById(R.id.recordBtn);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String grade = gradeET.getText().toString();
                String note = noteET.getText().toString();
                String studentName = firstName + " " + lastName;

                HashMap<String, Object> examHashMap = new HashMap<>();
                examHashMap.put("grade", grade);
                examHashMap.put("subject", subject);
                examHashMap.put("note", note);
                examHashMap.put("studentName", studentName);
                examHashMap.put("studentID", studentID);
                examHashMap.put("classID", classID);

                reference.setValue(examHashMap);
                reference.push();

                Toast.makeText(TeacherStandardRecordActivity.this, "Standard Exam Result Recorded", Toast.LENGTH_SHORT).show();
            }
        });















    }
}