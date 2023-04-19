package com.example.attex.parentmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAcademics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentViewTopicGradeActivity extends AppCompatActivity {

    TextView childGrade;
    TextView comment;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_topic_grade);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }



        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i2 = getIntent();
        String classGrade = i2.getStringExtra("classGrade");
        System.out.println(classGrade);

        Intent i3 = getIntent();
        String classID = i3.getStringExtra("classID");
        System.out.println(classID);

        // Intent i4 = getIntent();
        // String subject = i4.getStringExtra("subject");

        Intent i5 = getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println(studentID);

        Intent i6 = getIntent();
        String subject = i6.getStringExtra("subject");
        System.out.println(subject);

        Intent i7 = getIntent();
        String topic = i7.getStringExtra("topic");
        System.out.println(topic);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("AcademicRecord").child(schoolID).child(classGrade).child(classID).child(subject).child(topic).child(studentID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelAcademics grade = snapshot.getValue(ModelAcademics.class);
                childGrade = findViewById(R.id.grade);
                childGrade.setText("Grade: " + grade.getGrade() + "%");

                title = findViewById(R.id.title);
                title.setText(subject + " - " + topic);

                comment = findViewById(R.id.comment);
                comment.setText("Feedback: " + grade.getNote());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ArrayList<Float> list = new ArrayList<>();

        DatabaseReference ref = database.getReference("AcademicRecord").child(schoolID).child(classGrade).child(classID).child(subject).child(topic);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String grade = null;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelAcademics academics = dataSnapshot.getValue(ModelAcademics.class);
                     grade = academics.getGrade();
                    float gradeValue = Float.parseFloat(grade);

                    list.add(gradeValue);

                    /*float totalAverage = 0;
                    for (int i=0; i<list.size(); i++){
                        totalAverage = totalAverage + list.get(i);
                    }

                    float avg = totalAverage / list.size();
                    System.out.println(avg);
                    String avgString = Float.toString(avg);

                    TextView averageTV = findViewById(R.id.averageTV);
                    averageTV.setText("Class Average: " + avgString);*/
                }
                float totalAverage = 0;
                for (int i=0; i<list.size(); i++){
                    totalAverage = totalAverage + list.get(i);
                }

                float avg = totalAverage / list.size();
                System.out.println(avg);
                String avgString = Float.toString(avg);

                TextView averageTV = findViewById(R.id.averageTV);
                averageTV.setText("Class Average: " + avgString);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}