package com.example.attex.parentmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.attex.R;
import com.example.attex.models.ModelMedical;
import com.example.attex.models.ModelNote;
import com.example.attex.studentprofile.StudentBehavAddActivity;
import com.example.attex.studentprofile.StudentBehaviourActivity;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChildMedicalActivity extends AppCompatActivity {

    FloatingActionButton addMedical;
    RecyclerView recyclerView;
    ArrayList<ModelMedical> medList;
    MedicalListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_medical);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String studentID = i.getStringExtra("studentID");

        //recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medList = new ArrayList<>();
        adapter = new MedicalListAdapter(medList, this, schoolID, classGrade, classID, studentID);
        recyclerView.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MedicalInfo").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelMedical medical = dataSnapshot.getValue(ModelMedical.class);
                    medList.add(medical);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addMedical = findViewById(R.id.addMedical);
        addMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildMedicalActivity.this, ChildAddMedicalActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("classID", classID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });
    }
}