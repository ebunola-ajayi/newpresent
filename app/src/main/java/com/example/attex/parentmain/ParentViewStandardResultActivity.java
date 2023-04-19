package com.example.attex.parentmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

    TextView title;
    TextView grade;
    TextView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_standard_result);

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("StandardExamResults").child(schoolID).child(classGrade).child(classID).child(studentID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelAcademics academics = snapshot.getValue(ModelAcademics.class);

                title = findViewById(R.id.title);
                title.setText("Standard Exam Result");

                comment = findViewById(R.id.comment);
                comment.setText(academics.getNote());

                grade = findViewById(R.id.grade);
                grade.setText(academics.getGrade());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}