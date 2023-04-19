package com.example.attex.teacheracademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAcademics;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubjectViewAcademicActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SubjectViewAcademicAdapter adapter;
    ArrayList<ModelAcademics> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_view_academic);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i2 = getIntent();
        String teacherID = i2.getStringExtra("classID");
        System.out.println(teacherID);

        Intent i3 = getIntent();
        String subject = i3.getStringExtra("subject");
        System.out.println(subject);

        Intent i4 = getIntent();
        String schoolID = i4.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i5 = getIntent();
        String classGrade = i5.getStringExtra("classGrade");
        System.out.println(classGrade);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //get teacher's class id
        DatabaseReference reference = database.getReference("AcademicSubjects").child(schoolID).child(classGrade).child(teacherID).child(subject);

        subjectList = new ArrayList<>();
        adapter = new SubjectViewAcademicAdapter(subjectList, this, teacherID, subject, schoolID, classGrade);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    System.out.println(dataSnapshot.getValue());
                    ModelAcademics topic = dataSnapshot.getValue(ModelAcademics.class);

                    //String newid = student.getStudentID();
                    //System.out.println("Line 56" + task.toString());
                    subjectList.add(topic);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}