package com.example.attex.parentmain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelBehaviour;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentViewBehaviourActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2, recyclerView3;
    ChildBehaviorAdapter adapter, adapter2, adapter3;
    ArrayList<ModelBehaviour> behaviourList, positiveList, negativeList;
    Button positiveButton, negativeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_behaviour);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String studentID = i.getStringExtra("studentID");

        //setting recyclerView for all behaviours
        behaviourList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChildBehaviorAdapter(behaviourList, this);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelBehaviour behaviour = dataSnapshot.getValue(ModelBehaviour.class);
                    behaviourList.add(behaviour);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        //setting recyclerView2 for positive behaviours
        positiveList = new ArrayList<>();
        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new ChildBehaviorAdapter(positiveList, this);
        recyclerView3.setAdapter(adapter2);

        positiveButton = findViewById(R.id.positiveButton);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make the rcv3 visible and the other rcvs gone
                recyclerView3.setVisibility(View.VISIBLE);
                recyclerView2.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);

                Query query = FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID)
                        .orderByChild("feedback")
                        .equalTo("Positive");

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        positiveList.clear();
                        if(snapshot.exists()){
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                ModelBehaviour behaviour = dataSnapshot.getValue(ModelBehaviour.class);
                                positiveList.add(behaviour);
                            }
                            adapter2.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                query.addListenerForSingleValueEvent(valueEventListener);


            }
        });


        //setting recyclerView3 for negative behaviours
        negativeList = new ArrayList<>();
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter3 = new ChildBehaviorAdapter(negativeList, this);
        recyclerView2.setAdapter(adapter3);

        negativeButton = findViewById(R.id.negativeButton);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView2.setVisibility(View.VISIBLE);
                recyclerView3.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);

                Query query2 = FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID)
                        .orderByChild("feedback")
                        .equalTo("Negative");

                ValueEventListener valueEventListener2 = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        negativeList.clear();
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                ModelBehaviour behaviour = snapshot.getValue(ModelBehaviour.class);
                                negativeList.add(behaviour);

                            }
                            adapter3.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError){
                    }
                };
                query2.addListenerForSingleValueEvent(valueEventListener2);
            }
        });








    }
}