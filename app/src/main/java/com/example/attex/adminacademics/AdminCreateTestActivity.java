package com.example.attex.adminacademics;

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

public class AdminCreateTestActivity extends AppCompatActivity {

    TextView titleET;

    EditText question1ET;
    EditText question2ET;
    EditText question3ET;
    EditText question4ET;
    EditText question5ET;
    EditText question6ET;
    EditText question7ET;
    EditText question8ET;
    EditText question9ET;
    EditText question10ET;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_test);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i2 = getIntent();
        String subject = i2.getStringExtra("subject");
        System.out.println(subject);

        Intent i3 = getIntent();
        String year = i3.getStringExtra("year");
        System.out.println(year);

        Intent i4 = getIntent();
        String schoolID = i4.getStringExtra("schoolID");
        System.out.println(schoolID);

        titleET = findViewById(R.id.title);
        titleET.setText(classGrade + " - " + subject);

        question1ET = findViewById(R.id.question1);
        question2ET = findViewById(R.id.question2);
        question3ET = findViewById(R.id.question3);
        question4ET = findViewById(R.id.question4);
        question5ET = findViewById(R.id.question5);
        question6ET = findViewById(R.id.question6);
        question7ET = findViewById(R.id.question7);
        question8ET = findViewById(R.id.question8);
        question9ET = findViewById(R.id.question9);
        question10ET = findViewById(R.id.question10);

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("StandardExam").child(schoolID).child(classGrade).child(subject);

                String question1 = question1ET.getText().toString();
                String question2 = question2ET.getText().toString();
                String question3 = question3ET.getText().toString();
                String question4 = question4ET.getText().toString();
                String question5 = question5ET.getText().toString();
                String question6 = question6ET.getText().toString();
                String question7 = question7ET.getText().toString();
                String question8 = question8ET.getText().toString();
                String question9 = question9ET.getText().toString();
                String question10 = question10ET.getText().toString();
                String title = titleET.getText().toString();


                HashMap<String, Object> examQuestionsHashmap = new HashMap<>();
                examQuestionsHashmap.put("academicYear", year);
                examQuestionsHashmap.put("title", title);
                examQuestionsHashmap.put("question1", question1);
                examQuestionsHashmap.put("question2", question2);
                examQuestionsHashmap.put("question3", question3);
                examQuestionsHashmap.put("question4", question4);
                examQuestionsHashmap.put("question5", question5);
                examQuestionsHashmap.put("question6", question6);
                examQuestionsHashmap.put("question7", question7);
                examQuestionsHashmap.put("question8", question8);
                examQuestionsHashmap.put("question9", question9);
                examQuestionsHashmap.put("question10", question10);


                reference.setValue(examQuestionsHashmap);

            }
        });





    }
}