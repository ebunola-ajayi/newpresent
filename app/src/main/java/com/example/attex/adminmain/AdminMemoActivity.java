package com.example.attex.adminmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelMemo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminMemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminMemoAdapter adapter;
    FloatingActionButton addMemo;
    ArrayList<ModelMemo> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_memo);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        memoList = new ArrayList<>();
        adapter = new AdminMemoAdapter(memoList, this, schoolID);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Memos").child(schoolID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelMemo modelMemo = dataSnapshot.getValue(ModelMemo.class);
                    memoList.add(modelMemo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        addMemo = findViewById(R.id.addMemo);
        addMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMemoActivity.this, AdminAddMemoActivity.class);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });

    }
}