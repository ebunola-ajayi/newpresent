package com.example.attex.teacheracademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.attex.R;

public class TeacherViewStandardSubjectActivity extends AppCompatActivity {

    ImageView maths, english, irish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_standard_subject);

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String classID = i.getStringExtra("classID");

        maths = findViewById(R.id.maths);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Maths";
                //Intent intent = new Intent(TeacherViewStandardSubjectActivity.this, TeacherStandardOptionsActivity.class);
                Intent intent = new Intent(TeacherViewStandardSubjectActivity.this, TeacherViewStandardActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("subject", subject);
                startActivity(intent);

            }
        });


        english = findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "English";
                Intent intent = new Intent(TeacherViewStandardSubjectActivity.this, TeacherViewStandardActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        irish = findViewById(R.id.irish);
        irish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Irish";
                Intent intent = new Intent(TeacherViewStandardSubjectActivity.this, TeacherViewStandardActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });



    }
}