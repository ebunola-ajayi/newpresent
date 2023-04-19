package com.example.attex.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.models.ModelAcademics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentViewTopicsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentViewTopicsAdapter adapter;
    ArrayList<ModelAcademics> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_topics);

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
        String studentID = i4.getStringExtra("studentID");
        System.out.println(studentID);

        Intent i5 = getIntent();
        String subject = i5.getStringExtra("subject");
        System.out.println(subject);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topicList = new ArrayList<>();
        adapter = new StudentViewTopicsAdapter(topicList, this, schoolID, classGrade, classID, studentID, subject);
        recyclerView.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AcademicSubjects").child(schoolID).child(classGrade).child(classID).child(subject);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelAcademics topics = dataSnapshot.getValue(ModelAcademics.class);

                    topicList.add(topics);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}