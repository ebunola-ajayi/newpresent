package com.example.attex.parentmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attex.R;
import com.example.attex.models.ModelAdmin;
import com.example.attex.models.ModelParent;
import com.example.attex.teacherchat.ListParentsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentEnterSchoolIDActivity extends AppCompatActivity {

    Button next;
    EditText schoolIDET;
    ArrayList<ModelAdmin> adminList;
    ArrayList<String> idList;
    private FirebaseAuth newAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_enter_school_idactivity);

        newAuth = FirebaseAuth.getInstance();
        if(newAuth.getCurrentUser() != null){
            finish();
            return;
        }

        schoolIDET = findViewById(R.id.schoolIDET);
        String schoolID = schoolIDET.getText().toString();
        adminList = new ArrayList<>();
        idList = new ArrayList<>();

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check(schoolID);
       /*         Query query = FirebaseDatabase.getInstance().getReference("Admin").orderByChild("schoolID").equalTo(schoolID);


                query.addListenerForSingleValueEvent(valueEventListener);
*/

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin").child(schoolID);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //if(snapshot.exists()){
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            ModelAdmin admin = dataSnapshot.getValue(ModelAdmin.class);
                            System.out.println(admin.getSchoolID());
                            System.out.println("HELLO");
                        }

                       // }
                        /*else {
                            System.out.println("Not Exist");
                        }*/
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }

 /*   private void check(String schoolID) {
        Query query = FirebaseDatabase.getInstance().getReference("Admin")
                .orderByChild("schoolID")
                .equalTo(schoolID);
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            adminList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelAdmin admin  = snapshot.getValue(ModelAdmin.class);
                    adminList.add(admin);
                    idList.add(admin.getSchoolID());
                    System.out.println(idList);
                }
            } *//*else {
                Toast.makeText(ParentEnterSchoolIDActivity.this, "No ID Listed", Toast.LENGTH_SHORT).show();
            }*//*
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };*/


}