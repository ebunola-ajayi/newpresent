package com.example.attex.adminmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AdminStudentDetailsActivity extends AppCompatActivity {

    EditText firstNameET, lastNameET, middleNameET, dobET, parent1NameET, parent2NameET, parent1NoET, parent2NoET, parent1EmailET, parent2EmailET, addressLine1ET, addressLine2ET, countyET;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_details);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
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


        update = findViewById(R.id.update);
        firstNameET = findViewById(R.id.firstName);
        lastNameET = findViewById(R.id.lastName);
        middleNameET = findViewById(R.id.middleName);
        dobET = findViewById(R.id.dob);
        parent1NameET = findViewById(R.id.parent1Name);
        parent2NameET = findViewById(R.id.parent2Name);
        parent1NoET = findViewById(R.id.parent1No);
        parent2NoET = findViewById(R.id.parent2No);
        parent1EmailET = findViewById(R.id.parent1Email);
        parent2EmailET = findViewById(R.id.parent2Email);
        addressLine1ET = findViewById(R.id.addressLine1);
        addressLine2ET = findViewById(R.id.addressLine2);
        countyET = findViewById(R.id.county);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID).child(studentID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelStudent student = snapshot.getValue(ModelStudent.class);

                firstNameET.setText(student.getFirstName());
                lastNameET.setText(student.getLastName());
                middleNameET.setText(student.getMiddleName());
                dobET.setText(student.getDateOfBirth());
                parent1NameET.setText(student.getParent1Name());
                parent2NameET.setText(student.getParent2Name());
                parent1NoET.setText(student.getParent1No());
                parent2NoET.setText(student.getParent2No());
                parent1EmailET.setText(student.getParentEmail1());
                parent2EmailET.setText(student.getParentEmail2());
                addressLine1ET.setText(student.getAddressLine1());
                addressLine2ET.setText(student.getAddressLine2());
                countyET.setText(student.getCounty());

                String teacherEmail = student.getTeacherEmail();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String firstName = firstNameET.getText().toString();
                        String lastName = lastNameET.getText().toString();
                        String middleName = middleNameET.getText().toString();
                        String dateOfBirth = dobET.getText().toString();
                        String parent1Name = parent1NameET.getText().toString();
                        String parent2Name = parent2NameET.getText().toString();
                        String parent1No = parent1NoET.getText().toString();
                        String parent2No = parent2NoET.getText().toString();
                        String parent1Email = parent1EmailET.getText().toString();
                        String parent2Email = parent2EmailET.getText().toString();
                        String addressLine1No = addressLine1ET.getText().toString();
                        String addressLine2No = addressLine2ET.getText().toString();
                        String county = countyET.getText().toString();

                        if(firstName.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter A First Name", Toast.LENGTH_SHORT).show();
                            firstNameET.requestFocus();

                        }else if(lastName.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter a Last Name", Toast.LENGTH_SHORT).show();
                            lastNameET.requestFocus();

                        }else if(dateOfBirth.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter A Date Of Birth (DD/MM/YY)", Toast.LENGTH_SHORT).show();
                            dobET.requestFocus();

                        }else if(parent1Name.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter A Parent/ Guardian Name", Toast.LENGTH_SHORT).show();
                            parent1NameET.requestFocus();

                        }else if(parent1No.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter A Parent/ Guardian Number", Toast.LENGTH_SHORT).show();
                            parent1NoET.requestFocus();

                        }else if(parent1Email.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter A Parent/ Guardian Email", Toast.LENGTH_SHORT).show();
                            parent1EmailET.requestFocus();

                        }else if(addressLine1No.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter an Adress", Toast.LENGTH_SHORT).show();
                            addressLine1ET.requestFocus();

                        }else if(county.isEmpty()){
                            Toast.makeText(AdminStudentDetailsActivity.this, "Please Enter A County", Toast.LENGTH_SHORT).show();
                            countyET.requestFocus();

                        }else{
                            HashMap<String, Object> edittedHashmap = new HashMap<>();
                            edittedHashmap.put("firstName", firstName);
                            edittedHashmap.put("lastName", lastName);
                            edittedHashmap.put("middleName", middleName);
                            edittedHashmap.put("dateOfBirth", dateOfBirth);
                            edittedHashmap.put("parent1Name", parent1Name);
                            edittedHashmap.put("parent2Name", parent2Name);
                            edittedHashmap.put("parent1No", parent1No);
                            edittedHashmap.put("parent2No", parent2No);
                            edittedHashmap.put("parentEmail1", parent1Email);
                            edittedHashmap.put("parentEmail2", parent2Email);
                            edittedHashmap.put("addressLine1", addressLine1No);
                            edittedHashmap.put("addressLine2", addressLine2No);
                            edittedHashmap.put("county", county);
                            edittedHashmap.put("schoolID", schoolID);
                            edittedHashmap.put("classGrade", classGrade);
                            edittedHashmap.put("classID", classID);
                            edittedHashmap.put("studentID", studentID);
                            edittedHashmap.put("teacherEmail", teacherEmail);


                            reference.setValue(edittedHashmap);
                            Toast.makeText(AdminStudentDetailsActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                        }





                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}