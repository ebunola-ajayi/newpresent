package com.example.attex.adminacademics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.teacheracademics.SubjectViewAcademicActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminSelectSubjectActivity extends AppCompatActivity {

    ImageView maths, science, english, irish, geography, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_subject);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String classID = i.getStringExtra("classID");

        maths = findViewById(R.id.mathss);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Maths";

                Intent intent = new Intent(AdminSelectSubjectActivity.this, SubjectViewAcademicActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        science = findViewById(R.id.science);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Science";

                Intent intent = new Intent(AdminSelectSubjectActivity.this, SubjectViewAcademicActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        english = findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "English";

                Intent intent = new Intent(AdminSelectSubjectActivity.this, SubjectViewAcademicActivity.class);
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

                Intent intent = new Intent(AdminSelectSubjectActivity.this, SubjectViewAcademicActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });

        geography = findViewById(R.id.geography);
        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Geography";

                Intent intent = new Intent(AdminSelectSubjectActivity.this, SubjectViewAcademicActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "History";

                Intent intent = new Intent(AdminSelectSubjectActivity.this, SubjectViewAcademicActivity.class);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


    }
}