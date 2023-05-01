package com.example.attex.adminacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;

public class AdminViewTestActivity extends AppCompatActivity {

    TextView title;
    EditText question1ET, question2ET, question3ET, question4ET, question5ET, question6ET, question7ET, question8ET, question9ET, question10ET;
    Button update;
    String academicYear;

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
        String subject = i.getStringExtra("subject");

        question1ET = findViewById(R.id.question1);
        question2ET = findViewById(R.id.question2);
        question3ET = findViewById(R.id.question3);
        question4ET = findViewById(R.id.question4);
        question5ET = findViewById(R.id.question5);
        question6ET = findViewById(R.id.question6);
        question7ET = findViewById(R.id.question7);
        question8ET = findViewById(R.id.question8);
        question9ET = findViewById(R.id.question9);
        question10ET = findViewById(R.id.question10);
        title = findViewById(R.id.title);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StandardExam").child(schoolID).child(classGrade).child(subject);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                ModelStandardExam exam = snapshot.getValue(ModelStandardExam.class);

                title.setText(exam.getTitle());

                question1ET.setText(exam.getQuestion1());
                question2ET.setText(exam.getQuestion2());
                question3ET.setText(exam.getQuestion3());
                question4ET.setText(exam.getQuestion4());
                question5ET.setText(exam.getQuestion5());
                question6ET.setText(exam.getQuestion6());
                question7ET.setText(exam.getQuestion7());
                question8ET.setText(exam.getQuestion8());
                question9ET.setText(exam.getQuestion9());
                question10ET.setText(exam.getQuestion10());

                academicYear = exam.getAcdemicYear();
                System.out.println(academicYear);
                System.out.println(exam.getAcdemicYear());

            }else {
                Toast.makeText(AdminViewTestActivity.this, "No Test Created", Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question1 = question1ET.getText().toString();
                String question2 = question2ET.getText().toString();
                String question3 = question3ET.getText().toString();
                String question4 = question4ET.getText().toString();
                String question5 = question5ET.getText().toString();
                String question6 = question6ET.getText().toString();
                String question7 = question7ET.getText().toString();
                String question8 = question8ET.getText().toString();
                String question9 = question9ET.getText().toString();
                String question10 = question10ET.getText().toString();

                HashMap<String, Object> updateHashmap = new HashMap<>();
                updateHashmap.put("question1", question1);
                updateHashmap.put("question2", question2);
                updateHashmap.put("question3", question3);
                updateHashmap.put("question4", question4);
                updateHashmap.put("question5", question5);
                updateHashmap.put("question6", question6);
                updateHashmap.put("question7", question7);
                updateHashmap.put("question8", question8);
                updateHashmap.put("question9", question9);
                updateHashmap.put("question10", question10);
                updateHashmap.put("academicYear", academicYear);
                System.out.println(academicYear);

                reference.setValue(updateHashmap);

            }
        });





    }
}