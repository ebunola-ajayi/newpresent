package com.example.attex.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.models.ModelStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView firstNameTV, lastNameTV, middleNameTV, dobTV, parent1NameTV, parent2NameTV, parent1NoTV, parent2NoTV, parent1EmailTV, parent2EmailTV, addressLine1TV, addressLine2TV, countyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

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
        String classID = i.getStringExtra("classID");
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");

        firstNameTV = findViewById(R.id.firstName);
        lastNameTV = findViewById(R.id.lastName);
        middleNameTV = findViewById(R.id.middleName);
        dobTV = findViewById(R.id.dob);
        parent1NameTV = findViewById(R.id.parent1Name);
        parent2NameTV = findViewById(R.id.parent2Name);
        parent1NoTV = findViewById(R.id.parent1No);
        parent2NoTV = findViewById(R.id.parent2No);
        parent1EmailTV = findViewById(R.id.parent1Email);
        parent2EmailTV = findViewById(R.id.parent2Email);
        addressLine1TV = findViewById(R.id.addressLine1);
        addressLine2TV = findViewById(R.id.addressLine2);
        countyTV = findViewById(R.id.county);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelStudent student = snapshot.getValue(ModelStudent.class);

                firstNameTV.setText("First Name: " + student.getFirstName());
                lastNameTV.setText("Last Name: " + student.getLastName());
                middleNameTV.setText("Middle Name: " + student.getMiddleName());
                dobTV.setText("Date of Birth: " + student.getDateOfBirth());
                parent1NameTV.setText("Parent Name 1: " + student.getParent1Name());
                parent2NameTV.setText("Parent Name 2: " + student.getParent2Name());
                parent1NoTV.setText("Contact 1 Number: " + student.getParent1No());
                parent2NoTV.setText("Contact 2 Number: " + student.getParent2No());
                parent1EmailTV.setText("Parent 1 Email: " + student.getParentEmail1());
                parent2EmailTV.setText("Parent 2 Email: " + student.getParentEmail2());
                addressLine1TV.setText("Address Line 1: " + student.getAddressLine1());
                addressLine2TV.setText("Address Line 2: " + student.getAddressLine2());
                countyTV.setText("County: " + student.getCounty());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}