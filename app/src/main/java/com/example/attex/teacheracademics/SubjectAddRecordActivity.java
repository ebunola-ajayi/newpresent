package com.example.attex.teacheracademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SubjectAddRecordActivity extends AppCompatActivity {

    TextView nameET;
    EditText noteET;
    EditText gradeET;
    EditText topicET;
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_add_record);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        /*intent.putExtra("topic", topic);
                intent.putExtra("teacherID", teacherID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("subject", subject);
                intent.putExtra("classGrade", classGrade);*/

        Intent i5;
        i5 = getIntent();
        String subject = i5.getStringExtra("subject");
      //  System.out.println(subject);

        Intent i6;
        i6 = getIntent();
        String topic = i6.getStringExtra("topic");
        System.out.println(subject + ", " + topic);

        Intent i7;
        i7 = getIntent();
        String schoolID = i7.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i8;
        i8 = getIntent();
        String classGrade = i8.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i;
        i = getIntent();
        String studentID = i.getStringExtra("studentID");

        Intent i2;
        i2 = getIntent();
        String firstName = i2.getStringExtra("firstName");

        Intent i3;
        i3 = getIntent();
        String lastName = i3.getStringExtra("lastName");

        Intent i4;
        i4 = getIntent();
        String classID = i4.getStringExtra("classID");

        nameET = findViewById(R.id.studentName);
        nameET.setText(firstName + " " + lastName);

        noteET = findViewById(R.id.noteET);
        gradeET = findViewById(R.id.gradeET);
        topicET = findViewById(R.id.topic);
        topicET.setText(topic);


     //  Intent intent2 = getIntent();
       // String sub = intent2.getStringExtra(SubjectSelectStudentActivity.SUBJECT);


        //add to db!

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

                //Maths >Geometry, Algebra, Functions
                ref.setValue(subjectHashmap);
                //ref.setValue(topic);
                ref.push();

            }
        });






    }
}