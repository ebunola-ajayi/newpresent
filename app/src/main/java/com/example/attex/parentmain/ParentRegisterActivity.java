package com.example.attex.parentmain;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ParentRegisterActivity extends AppCompatActivity {
    private FirebaseAuth newAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);

        newAuth = FirebaseAuth.getInstance();
        if(newAuth.getCurrentUser() != null){
            finish();
            return;
        }

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerParent();
            }
        });

        TextView switchToLogin = findViewById(R.id.switchToLogin);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLoginPage();
            }
        });


    }

    private void registerParent() {
        EditText edtxtFirstName =findViewById(R.id.edtxtFirstName);
        EditText edtxtLastName =findViewById(R.id.edtxtLastName);
        EditText edtxtEmail =findViewById(R.id.edtxtEmail);
        EditText edtxtPassword =findViewById(R.id.edtxtPassword);
        EditText edtxtChildID =findViewById(R.id.edtxtChildID);
        EditText schoolIDET = findViewById(R.id.schoolIDET);
        EditText classGradeET = findViewById(R.id.classGradeET);
        EditText classIDET = findViewById(R.id.classIDET);
        EditText childFirstNameET = findViewById(R.id.childFirstNameET);
        EditText childLastNameET = findViewById(R.id.childLastNameET);


        String parentFirstName = edtxtFirstName.getText().toString();
        String parentLastName = edtxtLastName.getText().toString();
        String parentEmail = edtxtEmail.getText().toString();
        String parentPassword = edtxtPassword.getText().toString();
        String studentID = edtxtChildID.getText().toString();
        String schoolID = schoolIDET.getText().toString();
        String classGrade = classGradeET.getText().toString();
        String classID = classIDET.getText().toString();
        String childLastName = childLastNameET.getText().toString();
        String childFirstName = childFirstNameET.getText().toString();

        if(parentFirstName.isEmpty() || parentLastName.isEmpty() || parentEmail.isEmpty() || parentPassword.isEmpty() || studentID.isEmpty()){
            Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        newAuth.createUserWithEmailAndPassword(parentEmail, parentPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser firebaseUser = newAuth.getCurrentUser();
                    String parentID = firebaseUser.getUid();

                    HashMap<String, Object> parentHashmap = new HashMap<>();
                    parentHashmap.put("parentFirstName", parentFirstName);
                    parentHashmap.put("parentLastName", parentLastName);
                    parentHashmap.put("parentEmail", parentEmail);
                    parentHashmap.put("email", parentEmail);
                    parentHashmap.put("parentID", parentID);
                    parentHashmap.put("schoolID", schoolID);
                    parentHashmap.put("studentID", studentID);
                    parentHashmap.put("classGrade", classGrade);
                    parentHashmap.put("classID", classID);
                    parentHashmap.put("childLastName", childLastName);
                    parentHashmap.put("childFirstName", childFirstName);





                    FirebaseDatabase.getInstance().getReference("Parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(parentHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(ParentRegisterActivity.this, EnterChildIDActivity.class);
                                    intent.putExtra("schoolID", schoolID);
                                    intent.putExtra("classGrade", classGrade);
                                    intent.putExtra("classID", classID);
                                    startActivity(intent);
                                }
                            });
                }else {
                    Toast.makeText(ParentRegisterActivity.this, "Authentication Failed. Please Try Again", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void switchToLoginPage() {
        Intent intent = new Intent(this, ParentLoginActivity.class);
        startActivity(intent);
        finish();
    }
}