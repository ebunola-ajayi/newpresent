package com.example.attex.teachermain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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


    //Intent i = getIntent();
   // String subjectName = i.getStringExtra("subject");
    //public static final String SUBJECT = "subjectName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_select_student);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        //Intent Data
       /* Intent i = getIntent();
        String subjectName = i.getStringExtra("subject");

        Intent i2 = getIntent();
        String topic = i.getStringExtra("topic");*/
        
        
        
       /* Intent intent = new Intent(SubjectSelectStudentActivity.this, SubjectAddRecordActivity.class);
        intent.putExtra("subject", subjectName);
        intent.putExtra("topic", topic);*/



        /*intent.putExtra("topic", topic);
                intent.putExtra("teacherID", teacherID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("subject", subject);
                intent.putExtra("classGrade", classGrade);*/



        Intent i = getIntent();
        String subjectName = i.getStringExtra("subject");

        Intent i2 = getIntent();
        String topic = i2.getStringExtra("topic");

        Intent i3 = getIntent();
        String classGrade = i3.getStringExtra("classGrade");

        Intent i4 = getIntent();
        String classID = i4.getStringExtra("classID");

        Intent i5 = getIntent();
        String schoolID = i5.getStringExtra("schoolID");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Students").child(currentUser.getUid());

        studentLists = new ArrayList<>();
        adapter = new SubjectSelectStudentAdapter(studentLists, this, subjectName, topic, classGrade, classID, schoolID);
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