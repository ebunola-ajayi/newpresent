package com.example.attex.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.models.ModelAcademics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentGradeActivity extends AppCompatActivity {

    EditText noteET, gradeET;
    TextView studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String classID = i.getStringExtra("classID");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String studentID = i.getStringExtra("studentID");
        String subject = i.getStringExtra("subject");
        String topic = i.getStringExtra("topic");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AcademicRecord").child(schoolID).child(classGrade).child(classID).child(subject).child(topic).child(studentID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    ModelAcademics grades = snapshot.getValue(ModelAcademics.class);

                    noteET = findViewById(R.id.noteET);
                    noteET.setText(grades.getNote());

                    gradeET = findViewById(R.id.gradeET);
                    gradeET.setText(grades.getGrade());

                    studentName = findViewById(R.id.studentName);
                    studentName.setText(grades.getName());
                } else {
                    Toast.makeText(StudentGradeActivity.this, "No Record Entered", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}