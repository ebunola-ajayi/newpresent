package com.example.attex.teachermain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubjectSelectStudentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SubjectSelectStudentAdapter adapter;
    ArrayList<ModelStudent> studentLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_select_student);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        Intent i = getIntent();
        String subjectName = i.getStringExtra("subject");
        String topic = i.getStringExtra("topic");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String schoolID = i.getStringExtra("schoolID");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentLists = new ArrayList<>();
        adapter = new SubjectSelectStudentAdapter(studentLists, this, subjectName, topic, classGrade, classID, schoolID);
        recyclerView.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelStudent student = dataSnapshot.getValue(ModelStudent.class);
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