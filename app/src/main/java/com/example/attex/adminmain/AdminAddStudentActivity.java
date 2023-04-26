package com.example.attex.adminmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attex.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminAddStudentActivity extends AppCompatActivity {

    EditText firstNameET, lastNameET, middleNameET, dobET, parent1NameET, parent2NameET, parentEmail1ET, parentEmail2ET, parent1NoET, parent2NoET, addressLine1ET, addressLine2ET, countyET;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String teacherEmail = i.getStringExtra("teacherEmail");
        //String studentID;

        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        middleNameET = findViewById(R.id.middleNameET);
        dobET = findViewById(R.id.dobET);
        parent1NameET = findViewById(R.id.parent1NameET);
        parent2NameET = findViewById(R.id.parent2NameET);
        parentEmail1ET = findViewById(R.id.parentEmail1ET);
        parentEmail2ET = findViewById(R.id.parentEmail2ET);
        parent1NoET = findViewById(R.id.parent1NoET);
        parent2NoET = findViewById(R.id.parent2NoET);
        addressLine1ET = findViewById(R.id.addressLine1ET);
        addressLine2ET = findViewById(R.id.addressLine2ET);
        countyET = findViewById(R.id.countyET);

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameET.getText().toString();
                String lastName = lastNameET.getText().toString();
                String middleName = middleNameET.getText().toString();
                String dateOfBirth = dobET.getText().toString();
                String parent1Name = parent1NameET.getText().toString();
                String parent2Name = parent2NameET.getText().toString();
                String parentEmail1 = parentEmail1ET.getText().toString();
                String parentEmail2 = parentEmail2ET.getText().toString();
                String parent1No = parent1NoET.getText().toString();
                String parent2No = parent2NoET.getText().toString();
                String addressLine1 = addressLine1ET.getText().toString();
                String addressLine2 = addressLine2ET.getText().toString();
                String county = countyET.getText().toString();
                //String editDate; //01012001

                if(dateOfBirth.contains("/")){
                   dateOfBirth.replace("/", "-");//01-01-2023
                } else if (dateOfBirth.length() == 10){
                    Toast.makeText(AdminAddStudentActivity.this, "Date of Birth Format: DD/MM/YY", Toast.LENGTH_SHORT).show();
                    dobET.requestFocus();
                }else if(firstName.isEmpty() || lastName.isEmpty() || dateOfBirth.isEmpty() || parent1Name.isEmpty() || parentEmail1.isEmpty() || parent1No.isEmpty()){
                    Toast.makeText(AdminAddStudentActivity.this, "Please Enter All * Values", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID);

                    char one = firstName.charAt(0);
                    System.out.println(one);
                    char two = lastName.charAt(0);
                    char three = Character.toUpperCase(lastName.charAt(lastName.length() - 1));
                    char upThree = Character.toUpperCase(three);
                    String dateID = dateOfBirth.replace("-", "");
                    String studentID = String.valueOf(one) + String.valueOf(two) + String.valueOf(upThree) + dateID;
                    System.out.println(studentID);

                    HashMap<String, Object> studentHashmap = new HashMap<>();
                    studentHashmap.put("firstName", firstName);
                    studentHashmap.put("lastName", lastName);
                    studentHashmap.put("middleName", middleName);
                    studentHashmap.put("dateOfBirth", dateOfBirth);
                    studentHashmap.put("parent1Name", parent1Name);
                    studentHashmap.put("parent2Name", parent2Name);
                    studentHashmap.put("parentEmail1", parentEmail1);
                    studentHashmap.put("parentEmail2", parentEmail2);
                    studentHashmap.put("parent1No", parent1No);
                    studentHashmap.put("parent2No", parent2No);
                    studentHashmap.put("addressLine1", addressLine1);
                    studentHashmap.put("addressLine2", addressLine2);
                    studentHashmap.put("county", county);
                    studentHashmap.put("studentID", studentID);
                    studentHashmap.put("schoolID", schoolID);
                    studentHashmap.put("classGrade", classGrade);
                    studentHashmap.put("classID", classID);
                    studentHashmap.put("teacherEmail", teacherEmail);



                    reference.child(studentID).setValue(studentHashmap);
                    Toast.makeText(AdminAddStudentActivity.this, "Student Added Successfully", Toast.LENGTH_SHORT).show();

                    firstNameET.setText("");
                    lastNameET.setText("");
                    middleNameET.setText("");
                    dobET.setText("");
                    parent1NameET.setText("");
                    parent2NameET.setText("");
                    parentEmail1ET.setText("");
                    parentEmail2ET.setText("");
                    parent1NoET.setText("");
                    parent2NoET.setText("");
                    addressLine1ET.setText("");
                    addressLine2ET.setText("");
                    countyET.setText("");

                }
            }
        });







    }
}