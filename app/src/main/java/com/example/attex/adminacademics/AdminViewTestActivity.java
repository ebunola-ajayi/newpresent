package com.example.attex.adminacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.models.ModelStandardExam;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminViewTestActivity extends AppCompatActivity {

    TextView title;
    EditText question1, question2, question3, question4, question5, question6, question7, question8, question9, question10;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_test);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }



        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StandardExam").child(schoolID).child(classGrade).child("English");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelStandardExam exam = snapshot.getValue(ModelStandardExam.class);

                question1 = findViewById(R.id.question1);
                question2 = findViewById(R.id.question2);
                question3 = findViewById(R.id.question3);
                question4 = findViewById(R.id.question4);
                question5 = findViewById(R.id.question5);
                question6 = findViewById(R.id.question6);
                question7 = findViewById(R.id.question7);
                question8 = findViewById(R.id.question8);
                question9 = findViewById(R.id.question9);
                question10 = findViewById(R.id.question10);
                title = findViewById(R.id.title);

                title.setText(exam.getTitle());

                question1.setText(exam.getQuestion1());
                question2.setText(exam.getQuestion2());
                question3.setText(exam.getQuestion3());
                question4.setText(exam.getQuestion4());
                question5.setText(exam.getQuestion5());
                question6.setText(exam.getQuestion6());
                question7.setText(exam.getQuestion7());
                question8.setText(exam.getQuestion8());
                question9.setText(exam.getQuestion9());
                question10.setText(exam.getQuestion10());

                update = findViewById(R.id.update);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newQuestion;
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });





    }
}