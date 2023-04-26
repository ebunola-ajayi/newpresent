package com.example.attex.adminmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelTeacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminTeacherProfileActivity extends AppCompatActivity {

    TextView teacherNameTV, teacherIDTV, firstNameTV, lastNameTV, emailTV, classGradeTV, numberTV, addressLine1TV, addressLine2TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher_profile);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        teacherNameTV = findViewById(R.id.teacherName);
        teacherIDTV = findViewById(R.id.teacherID);
        firstNameTV = findViewById(R.id.firstName);
        lastNameTV = findViewById(R.id.lastName);
        emailTV = findViewById(R.id.email);
        classGradeTV = findViewById(R.id.classGradeTV);
        numberTV = findViewById(R.id.number);
        addressLine1TV = findViewById(R.id.addressLine1);
        addressLine2TV = findViewById(R.id.addressLine2);

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Teachers").child(schoolID).child(classGrade).child(classID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelTeacher teacher = snapshot.getValue(ModelTeacher.class);
                System.out.println(teacher.getEmail());

                teacherNameTV.setText(teacher.getTeacherName());
                teacherIDTV.setText(teacher.getClassID());
                firstNameTV.setText(teacher.getFirstName());
                lastNameTV.setText(teacher.getLastName());
                emailTV.setText(teacher.getEmail());
                classGradeTV.setText(classGrade);
                numberTV.setText(teacher.getNumber());
                addressLine1TV.setText(teacher.getAddressLine1());
                addressLine2TV.setText(teacher.getAddressLine2());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}