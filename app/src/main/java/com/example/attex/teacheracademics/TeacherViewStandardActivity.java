package com.example.attex.teacheracademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelStandardExam;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherViewStandardActivity extends AppCompatActivity {

    TextView question1;
    TextView question2;
    TextView question3;
    TextView question4;
    TextView question5;
    TextView question6;
    TextView question7;
    TextView question8;
    TextView question9;
    TextView question10;
    TextView title;

    Button recordResult, viewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_standard);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String subject = i.getStringExtra("subject");
        String classID = i.getStringExtra("classID");

        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);
        question7 = findViewById(R.id.question7);
        question8 = findViewById(R.id.question8);
        question9 = findViewById(R.id.question9);
        question10 = findViewById(R.id.question10);
        title = findViewById(R.id.title);

        recordResult = findViewById(R.id.recordResult);
        viewResult = findViewById(R.id.viewResult);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StandardExam").child(schoolID).child(classGrade).child(subject);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    ModelStandardExam exam = snapshot.getValue(ModelStandardExam.class);
                    question1.setText(exam.getQuestion1());
                    question2.setText(exam.getQuestion2());
                    question3.setText(exam.getQuestion3());
                    question4.setText(exam.getQuestion4());
                    question5.setText(exam.getQuestion5());
                    question6.setText(exam.getQuestion6());
                    question7.setText(exam.getQuestion7());
                    question8.setText(exam.getQuestion8());
                    question9.setText(exam.getQuestion9());
                    question10.setText(exam.getQuestion10());

                    title.setText(exam.getTitle());

                    recordResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TeacherViewStandardActivity.this, TeacherStandardSelectStudentsActivity.class);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classID", classID);
                            intent.putExtra("subject", subject);
                            intent.putExtra("classGrade", classGrade);
                            startActivity(intent);
                        }
                    });

                    viewResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TeacherViewStandardActivity.this, TeacherViewStandardResultActivity.class);
                            intent.putExtra("schoolID", schoolID);
                            intent.putExtra("classGrade", classGrade);
                            intent.putExtra("classID", classID);
                            intent.putExtra("subject", subject);
                            startActivity(intent);
                        }
                    });




                } else {
                    Toast.makeText(TeacherViewStandardActivity.this, "No Exam Created", Toast.LENGTH_SHORT).show();

                    recordResult.setVisibility(View.GONE);
                    viewResult.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}