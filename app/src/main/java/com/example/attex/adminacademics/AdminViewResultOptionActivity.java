package com.example.attex.adminacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.attex.InitialLoginActivity;
import com.example.attex.models.ModelTeacher;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminViewResultOptionActivity extends AppCompatActivity {

    Button juniorInfants;
    Button seniorInfants;
    Button firstClass;
    Button secondClass;
    Button thirdClass;
    Button fourthClass;
    Button fifthClass;
    Button sixthClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_result_option);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            //finish();
            //return;
        }

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("Teachers");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher = snapshot.getValue(ModelTeacher.class);

                String teacherID = teacher.getTeacherID();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        juniorInfants = findViewById(R.id.juniorInfants);
        juniorInfants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "Junior Infants";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });

        seniorInfants = findViewById(R.id.seniorInfants);
        seniorInfants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "Senior Infants";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });

        firstClass = findViewById(R.id.firstClass);
        firstClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "1st Class";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });

        secondClass = findViewById(R.id.secondClass);
        secondClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "2nd Class";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });

        thirdClass = findViewById(R.id.thirdClass);
        thirdClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "3rd Class";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });

        fourthClass = findViewById(R.id.fourthClass);
        fourthClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "4th Class";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });

        fifthClass = findViewById(R.id.fifthClass);
        fifthClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "5th Class";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });


        sixthClass = findViewById(R.id.sixthClass);
        sixthClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classGrade = "6th Class";
                Intent intent = new Intent(AdminViewResultOptionActivity.this, AdminSelectClassActivity.class);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
            }
        });
    }
}