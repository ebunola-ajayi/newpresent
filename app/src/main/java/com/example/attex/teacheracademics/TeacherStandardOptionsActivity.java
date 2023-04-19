package com.example.attex.teacheracademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherStandardOptionsActivity extends AppCompatActivity {

    Button viewExam;
    Button recordResult;
    Button viewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_standard_options);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i2 = getIntent();
        String schoolID = i2.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i3 = getIntent();
        String teacherID = i3.getStringExtra("teacherID");
        System.out.println(teacherID);

        viewExam = findViewById(R.id.viewExam);
        viewExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherStandardOptionsActivity.this, TeacherViewStandardActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });

        viewResult = findViewById(R.id.viewResult);
        viewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherStandardOptionsActivity.this, TeacherViewStandardResultActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("teacherID", teacherID);
                startActivity(intent);
            }
        });


        recordResult = findViewById(R.id.recordResult);
        recordResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherStandardOptionsActivity.this, TeacherStandardSelectStudentsActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("teacherID", teacherID);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });
    }
}