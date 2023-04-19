package com.example.attex.teacherchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.models.ModelParent;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListParentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView hello;
    ListParentsAdapter adapter;
    ArrayList<ModelParent> parentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parents);


        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();


        if(currentUser==null){
            Intent intent=new Intent(ListParentsActivity.this, InitialLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }

        hello = findViewById(R.id.hello);

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(schoolID);


        Intent i2 = getIntent();
        String classID = i2.getStringExtra("teacherID");
        System.out.println(classID);

        Intent i3 = getIntent();
        String classGrade = i3.getStringExtra("classGrade");
        System.out.println(classGrade);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        parentList = new ArrayList<>();
        adapter = new ListParentsAdapter(this, parentList);
        recyclerView.setAdapter(adapter);

        Query query = FirebaseDatabase.getInstance().getReference("Parents")
                .orderByChild("classID")
                .equalTo(classID);

        query.addListenerForSingleValueEvent(valueEventListener);


    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            parentList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelParent parent  = snapshot.getValue(ModelParent.class);
                    System.out.println(parent);
                    parentList.add(parent);
                    //name.setText(student.firstName);



                }
                //adapter.notifyDataSetChanged();
            } else {
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


}