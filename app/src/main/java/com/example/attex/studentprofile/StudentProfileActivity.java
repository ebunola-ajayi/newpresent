package com.example.attex.studentprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attex.R;
import com.example.attex.adminmain.StudentMedicalActivity;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentProfileActivity extends AppCompatActivity {

    TextView studentName, studentNumber;
    ImageView studentDetails, viewAcademic, viewBehav, medical, viewAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        studentName = findViewById(R.id.studentName);
        studentNumber = findViewById(R.id.studentNumber);



        Intent i;
        i = getIntent();
        String studentID = i.getStringExtra("studentID");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String firstName = i.getStringExtra("firstName");
        String lastName = i.getStringExtra("lastName");

        studentName.setText(firstName + " " + lastName);
        studentNumber.setText(studentID);

        studentDetails = findViewById(R.id.studentDetails);
        studentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentDetailsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

        viewAcademic = findViewById(R.id.viewAca);
        viewAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentSubjectsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

        viewAttendance = findViewById(R.id.viewAttendance);
        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentAttendanceActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        });

        viewBehav = findViewById(R.id.viewBehav);
        viewBehav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentBehaviourActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classID", classID);
                startActivity(intent);
            }
        });

        medical = findViewById(R.id.medical);
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfileActivity.this, StudentMedicalActivity.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classID", classID);
                startActivity(intent);
            }
        });



    }
}