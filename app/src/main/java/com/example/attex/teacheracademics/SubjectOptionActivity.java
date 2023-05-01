package com.example.attex.teacheracademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.teachermain.SubjectSelectStudentActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SubjectOptionActivity extends AppCompatActivity{

    ImageView addAcademic, viewAcademic;
    EditText topicName;
    TextView subName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_option);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        Intent i = getIntent();
        String subject = i.getStringExtra("subjectName");
        String classID = i.getStringExtra("classID");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");

        subName = findViewById(R.id.subName);
        subName.setText(subject);


        topicName = findViewById(R.id.newTopic);



        viewAcademic = findViewById(R.id.viewAcademic);
        viewAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectOptionActivity.this, SubjectViewAcademicActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("subject", subject);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                startActivity(intent);
            }
        });


        addAcademic = findViewById(R.id.addAcademic);
        addAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = topicName.getText().toString();

                if(topic.isEmpty()){
                    Toast.makeText(SubjectOptionActivity.this, "Please Enter A Topic Name", Toast.LENGTH_SHORT).show();
                    topicName.requestFocus();
                } else {

                    Intent intent = new Intent(SubjectOptionActivity.this, SubjectSelectStudentActivity.class);
                    intent.putExtra("topic", topic);
                    intent.putExtra("classID", classID);
                    intent.putExtra("schoolID", schoolID);
                    intent.putExtra("subject", subject);
                    intent.putExtra("classGrade", classGrade);
                    startActivity(intent);
                }

            }
        });



    }
}