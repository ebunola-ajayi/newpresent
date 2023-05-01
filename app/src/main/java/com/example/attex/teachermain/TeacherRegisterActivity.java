package com.example.attex.teachermain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TeacherRegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        //initialising firebase auth
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        Button regBtn = findViewById(R.id.registerButton);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        TextView switchToLogin = findViewById(R.id.switchToLogin);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage();
            }
        });


    }

    private void registerUser(){
        EditText etFirstName = findViewById(R.id.edtxtFirstName);
        EditText etLastName = findViewById(R.id.edtxtLastName);
        EditText etEmail = findViewById(R.id.edtxtEmail);
        EditText etPassword = findViewById(R.id.edtxtPassword);
        EditText tName = findViewById(R.id.teacherName);
        EditText teachID = findViewById(R.id.teachID);
        EditText schoolIDET = findViewById(R.id.schoolID);
        EditText classGradeET = findViewById(R.id.classGrade);

        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String teacherName = tName.getText().toString();
        String classID = teachID.getText().toString();
        String schoolID = schoolIDET.getText().toString();
        String classGrade = classGradeET.getText().toString();   // add conditions for this


        //error message for teacher name - cant contain space
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || teacherName.isEmpty() || classID.isEmpty()) {
            Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            HashMap<String, Object> teacherHashmap = new HashMap<>();
                            teacherHashmap.put("firstName", firstName);
                            teacherHashmap.put("lastName", lastName);
                            teacherHashmap.put("email", email);
                            teacherHashmap.put("teacherName", teacherName);
                            teacherHashmap.put("classID", classID);
                            teacherHashmap.put("schoolID", schoolID);
                            teacherHashmap.put("classGrade", classGrade);


                            DatabaseReference reference =FirebaseDatabase.getInstance().getReference("Teachers").child(schoolID).child(classGrade).child(classID);
                                    reference.setValue(teacherHashmap);

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TeacherDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            ref.setValue(teacherHashmap);
                            ref.push();

                            //showMainActivity();
                            Intent intent = new Intent(TeacherRegisterActivity.this, TeacherMainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(TeacherRegisterActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }


    private void loginPage(){
        Intent intent = new Intent(this, TeacherLoginActivity.class);
        startActivity(intent);
        finish();
    }
}