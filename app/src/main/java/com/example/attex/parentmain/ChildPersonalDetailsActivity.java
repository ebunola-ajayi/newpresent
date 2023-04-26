package com.example.attex.parentmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ChildPersonalDetailsActivity extends AppCompatActivity {

    EditText firstName, middleName, lastName, dob, studentIDET, parent1Name, parent2Name,  parent1No, parent2No, teachID;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_personal_details);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String studentID = i.getStringExtra("studentID");


        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        middleName = findViewById(R.id.middleName);
        dob = findViewById(R.id.dob);
        studentIDET = findViewById(R.id.studentIDET);
        parent1Name = findViewById(R.id.parent1Name);
        parent2Name = findViewById(R.id.parent2Name);
        parent1No = findViewById(R.id.parent1No);
        parent2No = findViewById(R.id.parent2No);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelStudent student = snapshot.getValue(ModelStudent.class);
                System.out.println(student);

                firstName.setText(student.getFirstName());
                lastName.setText(student.getLastName());
                middleName.setText(student.getMiddleName());
                dob.setText(student.getDateOfBirth());
                studentIDET.setText(studentID);
                parent1Name.setText(student.getParent1Name());
                parent2Name.setText(student.getParent2Name());
                parent1No.setText(student.getParent1No());
                parent2No.setText(student.getParent2No());

                update = findViewById(R.id.update);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String firstNameUp = firstName.getText().toString();
                        String lastNameUp = lastName.getText().toString();
                        String middleNameUp = middleName.getText().toString();
                        String dateOfBirth = dob.getText().toString();
                        String parent1NameUp = parent1Name.getText().toString();
                        String parent2NameUp = parent2Name.getText().toString();
                        String parent1NoUp = parent1No.getText().toString();
                        String parent2NoUp = parent2No.getText().toString();

                        HashMap<String, Object> updateHashmap = new HashMap<>();


                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}