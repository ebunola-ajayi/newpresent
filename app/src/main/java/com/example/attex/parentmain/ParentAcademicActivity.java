
package com.example.attex.parentmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ParentAcademicActivity extends AppCompatActivity {

    ImageView maths;
    ImageView science;
    ImageView english;
    ImageView geography;
    ImageView standardTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_academic);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }



        Intent i = getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println(studentID);

        Intent i2 = getIntent();
        String classID = i2.getStringExtra("classID");
        System.out.println(classID);

        Intent i3 = getIntent();
        String classGrade = i3.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i4 = getIntent();
        String schoolID = i4.getStringExtra("schoolID");
        System.out.println(schoolID);



        maths = findViewById(R.id.maths);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Maths";

                Intent intent = new Intent(ParentAcademicActivity.this, ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        science = findViewById(R.id.science);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Science";

                Intent intent = new Intent(ParentAcademicActivity.this, ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        english = findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "English";

                Intent intent = new Intent(ParentAcademicActivity.this, ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });

        geography = findViewById(R.id.geography);
        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Geography";

                Intent intent = new Intent(ParentAcademicActivity.this, ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });

        standardTest = findViewById(R.id.standardTest);
        standardTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentAcademicActivity.this, ParentViewStandardResultActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });




    }
}
