package com.example.attex.adminacademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminCreateTestActivity extends AppCompatActivity {

    TextView titleET;
    EditText question1ET, question2ET, question3ET, question4ET, question5ET, question6ET, question7ET, question8ET, question9ET, question10ET;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_test);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String subject = i.getStringExtra("subject");
        String year = i.getStringExtra("year");
        String schoolID = i.getStringExtra("schoolID");

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
                examQuestionsHashmap.put("subject", subject);
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