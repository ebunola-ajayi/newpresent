package com.example.attex.parentmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAcademics;
import com.example.attex.parentmain.ParentViewSubjectsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentViewTopicsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ParentViewSubjectsAdapter adapter;
    ArrayList<ModelAcademics> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_subjects);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();

        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i2 = getIntent();
        String classGrade = i2.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i3 = getIntent();
        String classID = i3.getStringExtra("classID");
        System.out.println(classID);

       // Intent i4 = getIntent();
       // String subject = i4.getStringExtra("subject");

        Intent i5 = getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println("topic" +studentID);

        Intent i6 = getIntent();
        String subject = i6.getStringExtra("subject");
        System.out.println(subject);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        subjectList = new ArrayList<>();
        adapter = new ParentViewSubjectsAdapter(subjectList, this, schoolID, classGrade, classID, studentID, subject);
        recyclerView.setAdapter(adapter);



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AcademicSubjects").child(schoolID).child(classGrade).child(classID).child(subject);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelAcademics subject = dataSnapshot.getValue(ModelAcademics.class);

                    subjectList.add(subject);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}