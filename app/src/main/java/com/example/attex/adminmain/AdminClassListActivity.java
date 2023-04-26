package com.example.attex.adminmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attex.R;
import com.example.attex.models.ModelTeacher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminClassListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminClassListAdapter adapter;
    ArrayList<ModelTeacher> teacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_class_list);

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherList = new ArrayList<>();
        adapter = new AdminClassListAdapter(teacherList, this, schoolID, classGrade);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Teachers").child(schoolID).child(classGrade);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelTeacher teacher = dataSnapshot.getValue(ModelTeacher.class);
                    teacherList.add(teacher);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}