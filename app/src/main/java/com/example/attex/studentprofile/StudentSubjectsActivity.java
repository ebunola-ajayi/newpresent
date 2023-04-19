package com.example.attex.studentprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentSubjectsActivity extends AppCompatActivity {

    ImageView maths;
    ImageView history;
    ImageView irish;
    ImageView geography;
    ImageView english;
    ImageView science;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subjects);

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
        String studentID = i3.getStringExtra("studentID");
        System.out.println(studentID);


//SubjectViewAcademicAdapter
        maths = findViewById(R.id.maths);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Maths";
                Intent intent = new Intent(StudentSubjectsActivity.this, StudentViewTopicsActivity.class);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

        science = findViewById(R.id.science);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Science";
                Intent intent = new Intent(StudentSubjectsActivity.this, StudentViewTopicsActivity.class);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });


        english = findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "English";
                Intent intent = new Intent(StudentSubjectsActivity.this, StudentViewTopicsActivity.class);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

        irish = findViewById(R.id.irish);
        irish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Irish";
                Intent intent = new Intent(StudentSubjectsActivity.this, StudentViewTopicsActivity.class);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });


        history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "History";
                Intent intent = new Intent(StudentSubjectsActivity.this, StudentViewTopicsActivity.class);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

        geography = findViewById(R.id.geography);
        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Geography";
                Intent intent = new Intent(StudentSubjectsActivity.this, StudentViewTopicsActivity.class);
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