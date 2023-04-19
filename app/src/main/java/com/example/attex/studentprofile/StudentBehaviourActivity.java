package com.example.attex.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    RecyclerView recyclerView;

    //used to help counting negative feedback
    RecyclerView recyclerView2;

    StudentBehaviourAdapter adapter;
    ArrayList<ModelBehaviour> behaviourList;
    ArrayList<ModelBehaviour> feedback;

    FeedbackAdapter feedbackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_behaviour);


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
        //i = getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println("HEY " + studentID);
        String firstName = i.getStringExtra("firstName");
        System.out.println(firstName);
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(schoolID);
        String classGrade = i.getStringExtra("classGrade");
        System.out.println(classGrade);
        String lastName = i.getStringExtra("lastName");

        String teacherID = i.getStringExtra("teacherID");
        System.out.println(teacherID);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        behaviourList = new ArrayList<>();
        adapter = new StudentBehaviourAdapter(this, behaviourList, schoolID, classGrade, teacherID, studentID);
        recyclerView.setAdapter(adapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();



        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        feedback = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(this, feedback);
        recyclerView2.setAdapter(feedbackAdapter);




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(teacherID).child(studentID);
        //DatabaseReference reference = database.getReference("BehaviourRecord").child(studentID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    //System.out.println(snapshot.getValue());
                    ModelBehaviour behaviour = dataSnapshot.getValue(ModelBehaviour.class);

                    String stuID = behaviour.getStudentID();
                  behaviourList.add(behaviour);

                }
                adapter.notifyDataSetChanged();
                System.out.println("test" + adapter.getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        addBehaviour = findViewById(R.id.addBehaviour);
        addBehaviour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i;
                i = getIntent();
                String studentID = i.getStringExtra("studentID");
                String firstName = i.getStringExtra("firstName");
                String lastName = i.getStringExtra("lastName");
                String teacherID = i.getStringExtra("teacherID");
                String schoolID = i.getStringExtra("schoolID");
                String classGrade = i.getStringExtra("classGrade");


                Intent intent = new Intent(StudentBehaviourActivity.this, StudentBehavAddActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("classID", teacherID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });







        Query query=FirebaseDatabase.getInstance().getReference("BehaviourRecord").child(schoolID).child(classGrade).child(teacherID).child(studentID)
                .orderByChild("feedback")
                .equalTo("Negative");

        query.addListenerForSingleValueEvent(valueEventListener);


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //behaviourList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelBehaviour behaviour = snapshot.getValue(ModelBehaviour.class);
                    feedback.add(behaviour);

                }
                feedbackAdapter.notifyDataSetChanged();
                System.out.println("Negatives: " + feedbackAdapter.getItemCount());

                if(feedbackAdapter.getItemCount() > 5){
                    Toast.makeText(StudentBehaviourActivity.this, "This student has more than 5 negative behavior reports. It is advised to speak to their parents", Toast.LENGTH_LONG).show();
                }


            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError){

        }
    };




}