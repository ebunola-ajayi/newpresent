package com.example.attex.adminmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelStudent;
import com.example.attex.models.ModelTeacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminListClassesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminListClassesAdapter adapter;
    ArrayList<ModelTeacher> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_classes);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        // ADMINID = currentUser.getUid();


        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classList = new ArrayList<>();
        adapter = new AdminListClassesAdapter(this, classList, classGrade, schoolID);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Teachers").child(schoolID).child(classGrade);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelTeacher listOfClasses = dataSnapshot.getValue(ModelTeacher.class);

                    classList.add(listOfClasses);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}