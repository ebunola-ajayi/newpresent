package com.example.attex.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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

    EditText noteET;
    EditText gradeET;
    TextView studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String classID = i.getStringExtra("classID");
        System.out.println(classID);

        Intent i2 = getIntent();
        String schoolID = i2.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i3 = getIntent();
        String classGrade = i3.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i4 = getIntent();
        String studentID = i4.getStringExtra("studentID");
        System.out.println(studentID);

        Intent i5 = getIntent();
        String subject = i5.getStringExtra("subject");
        System.out.println(subject);

        Intent i6 = getIntent();
        String topic = i6.getStringExtra("topic");
        System.out.println(topic);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AcademicRecord").child(schoolID).child(classGrade).child(classID).child(subject).child(topic).child(studentID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelAcademics grades = snapshot.getValue(ModelAcademics.class);

                noteET = findViewById(R.id.noteET);
                noteET.setText(grades.getNote());

                gradeET = findViewById(R.id.gradeET);
                gradeET.setText(grades.getGrade());

                studentName = findViewById(R.id.studentName);
                studentName.setText(grades.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}