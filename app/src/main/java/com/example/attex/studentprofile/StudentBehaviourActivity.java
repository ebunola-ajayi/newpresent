package com.example.attex.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
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

public class StudentBehaviourActivity extends AppCompatActivity {

    ImageView addBehaviour;
    Button positiveButton, negativeButton;

    //used to help counting negative feedback
    RecyclerView recyclerView, recyclerView2, recyclerView3;

    //adapters for respective recyclerViews
    StudentBehaviourAdapter adapter, adapter2, adapter3;

    //list for all behaviours, positive behavious and negative behaviours
    ArrayList<ModelBehaviour> behaviourList, positiveList, negativeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_behaviour);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String studentID = i.getStringExtra("studentID");
        String firstName = i.getStringExtra("firstName");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String lastName = i.getStringExtra("lastName");
        String classID = i.getStringExtra("classID");


        //setting the rcv for all behaviours
        behaviourList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentBehaviourAdapter(this, behaviourList, schoolID, classGrade, classID, studentID);
        recyclerView.setAdapter(adapter);

        //open db and add values for all behaviours
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelBehaviour behaviour = dataSnapshot.getValue(ModelBehaviour.class);
                    behaviourList.add(behaviour);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //setting rcv3 for the positive behaviours
        positiveList = new ArrayList<>();
        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new StudentBehaviourAdapter(StudentBehaviourActivity.this, positiveList, schoolID, classGrade, classID, studentID);
        recyclerView3.setAdapter(adapter2);

        positiveButton = findViewById(R.id.positiveButton);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make the rcv3 visible and the other rcvs gone
                recyclerView3.setVisibility(View.VISIBLE);
                recyclerView2.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);

                Query query2=FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID)
                        .orderByChild("feedback")
                        .equalTo("Positive");

                ValueEventListener valueEventListener2 = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        positiveList.clear();
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                ModelBehaviour behaviour = snapshot.getValue(ModelBehaviour.class);
                                positiveList.add(behaviour);
                            }
                            adapter2.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError){
                    }
                };
                query2.addListenerForSingleValueEvent(valueEventListener2);

            }
        });

        negativeList = new ArrayList<>();
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter3 = new StudentBehaviourAdapter(StudentBehaviourActivity.this, negativeList, schoolID, classGrade, classID, studentID);
        recyclerView2.setAdapter(adapter3);

        negativeButton = findViewById(R.id.negativeButton);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView2.setVisibility(View.VISIBLE);
                recyclerView3.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);

                Query query=FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(classID).child(studentID)
                        .orderByChild("feedback")
                        .equalTo("Negative");

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        negativeList.clear();
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                ModelBehaviour behaviour = snapshot.getValue(ModelBehaviour.class);
                                negativeList.add(behaviour);

                            }
                            if(negativeList.size() > 5){
                                Toast.makeText(StudentBehaviourActivity.this, "This student has more than 5 negative records. \n Consider speaking to parents", Toast.LENGTH_SHORT).show();
                            }

                            adapter3.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError){
                    }
                };
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });

        addBehaviour = findViewById(R.id.addBehaviour);
        addBehaviour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentBehaviourActivity.this, StudentBehavAddActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("classID", classID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });

    }
}