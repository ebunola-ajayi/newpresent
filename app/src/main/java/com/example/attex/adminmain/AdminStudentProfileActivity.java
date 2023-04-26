package com.example.attex.adminmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attex.R;
import com.example.attex.parentmain.ParentViewBehaviourActivity;
import com.example.attex.studentprofile.StudentAttendanceActivity;
import com.example.attex.studentprofile.StudentSubjectsActivity;

public class AdminStudentProfileActivity extends AppCompatActivity {

    ImageView viewAttendance, viewAca, viewBehav, studentDetails, medical;
    TextView studentName, studentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_profile);

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String studentID = i.getStringExtra("studentID");
        String firstName = i.getStringExtra("firstName");
        String lastName = i.getStringExtra("lastName");
        String studentFullName = firstName + " " + lastName;
        studentName = findViewById(R.id.studentName);
        studentName.setText(firstName + " " + lastName);

        studentNumber = findViewById(R.id.studentNumber);
        studentNumber.setText(studentID);

        viewAttendance = findViewById(R.id.viewAttendance);
        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminStudentProfileActivity.this, StudentAttendanceActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("studentName", studentFullName);
                startActivity(intent);
            }
        });


        viewAca = findViewById(R.id.viewAca);
        viewAca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminStudentProfileActivity.this, StudentSubjectsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

        viewBehav = findViewById(R.id.viewBehav);
        viewBehav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminStudentProfileActivity.this, ParentViewBehaviourActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);

                startActivity(intent);
            }
        });

        studentDetails = findViewById(R.id.studentDetails);
        studentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminStudentProfileActivity.this, AdminStudentDetailsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);

                startActivity(intent);

            }
        });

        medical = findViewById(R.id.medical);
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminStudentProfileActivity.this, StudentMedicalActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);

                startActivity(intent);
            }
        });

    }
}