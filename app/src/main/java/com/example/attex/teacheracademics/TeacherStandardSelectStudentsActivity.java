package com.example.attex.teacheracademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attex.R;
import com.example.attex.models.ModelStudent;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherStandardSelectStudentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TeacherStandardSelectStudentsAdapter adapter;
    ArrayList<ModelStudent> studentLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_standard_select_students);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Students").child(currentUser.getUid());

        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");

        Intent i2 = getIntent();
        String schoolID = i2.getStringExtra("schoolID");

        Intent i3 = getIntent();
        String teacherID = i3.getStringExtra("teacherID");


        studentLists = new ArrayList<>();
        adapter = new TeacherStandardSelectStudentsAdapter(studentLists, this, classGrade, schoolID, teacherID);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    System.out.println(dataSnapshot.getValue());
                    ModelStudent student = dataSnapshot.getValue(ModelStudent.class);

                    String stuID = student.getStudentID();
                    studentLists.add(student);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}