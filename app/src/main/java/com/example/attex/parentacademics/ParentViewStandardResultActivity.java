package com.example.attex.parentacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAcademics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentViewStandardResultActivity extends AppCompatActivity {

    TextView title, grade, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_standard_result);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }


        Intent i = getIntent();
        String studentID = i.getStringExtra("studentID");
        String classID = i.getStringExtra("classID");
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String subject = i.getStringExtra("subject");
        System.out.println(schoolID);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("StandardExamResults").child(schoolID).child(classGrade).child(classID).child(subject).child(studentID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    ModelAcademics academics = snapshot.getValue(ModelAcademics.class);

                    title = findViewById(R.id.title);
                    title.setText("Standard Exam Result - " + subject);

                    comment = findViewById(R.id.comment);
                    comment.setText(academics.getNote());

                    grade = findViewById(R.id.grade);
                    grade.setText(academics.getGrade());
                }else {
                    Toast.makeText(ParentViewStandardResultActivity.this, "No Grade Recorded", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}