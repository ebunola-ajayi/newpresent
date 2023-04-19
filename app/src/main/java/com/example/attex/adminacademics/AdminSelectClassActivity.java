package com.example.attex.adminacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.attex.models.ModelTeacher;
import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminSelectClassActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminSelectClassAdapter adapter;
    ArrayList<ModelTeacher> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_class);

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
        String classGrade = i.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i2 = getIntent();
        String schoolID = i2.getStringExtra("schoolID");
        System.out.println(schoolID);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classList = new ArrayList<>();
        adapter = new AdminSelectClassAdapter(classList, this, classGrade, schoolID);
        recyclerView.setAdapter(adapter);




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Teachers").child(schoolID).child(classGrade);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelTeacher teacher = dataSnapshot.getValue(ModelTeacher.class);
                    String teacherID = teacher.getTeacherID();

                    classList.add(teacher);
                }
                //ModelTeacher teacher = dataSnapshot.getValue(ModelTeacher.class);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}