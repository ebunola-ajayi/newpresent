package com.example.attex.teacheracademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SubjectAddRecordActivity extends AppCompatActivity {

    TextView nameET;
    EditText noteET, gradeET, topicET;
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_add_record);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        Intent i = getIntent();
        String subject = i.getStringExtra("subject");
        String topic = i.getStringExtra("topic");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String studentID = i.getStringExtra("studentID");
        String firstName = i.getStringExtra("firstName");
        String lastName = i.getStringExtra("lastName");
        String classID = i.getStringExtra("classID");

        nameET = findViewById(R.id.studentName);
        nameET.setText(firstName + " " + lastName);

        noteET = findViewById(R.id.noteET);
        gradeET = findViewById(R.id.gradeET);
        topicET = findViewById(R.id.topic);
        topicET.setText(topic);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("AcademicRecord").child(schoolID).child(classGrade).child(classID).child(subject).child(topic);

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String topic = topicET.getText().toString();
                String note = noteET.getText().toString();
                String grade = gradeET.getText().toString();


                HashMap<String, Object> academicHashmap = new HashMap<>();
                academicHashmap.put("topic", topic);
                academicHashmap.put("note", note);
                academicHashmap.put("grade", grade);
                academicHashmap.put("studentID", studentID);
                academicHashmap.put("name", firstName + " "+ lastName);

                reference.child(studentID).setValue(academicHashmap);


                //subject list saved
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("AcademicSubjects").child(schoolID).child(classGrade).child(classID).child(subject).child(topic);
                HashMap<String, Object> subjectHashmap = new HashMap<>();
                subjectHashmap.put("topic", topic);

                ref.setValue(subjectHashmap);
                ref.push();

            }
        });






    }
}