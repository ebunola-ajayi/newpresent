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
//WHEN VERIFYING SCHOOLID, CLASSID AND CHILDID, MAKE A NEW ACTIVITY (THAT CONFIRMS THOSE DETAILS EXIST) THAT THEN LEADS TO THIS ONE
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
        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        EditText edtxtFirstName =findViewById(R.id.edtxtFirstName);
        EditText edtxtLastName =findViewById(R.id.edtxtLastName);
        EditText edtxtEmail =findViewById(R.id.edtxtEmail);
        EditText edtxtPassword =findViewById(R.id.edtxtPassword);
        EditText edtxtChildID =findViewById(R.id.edtxtChildID);
        EditText username = findViewById(R.id.username);
        //EditText schoolIDET = findViewById(R.id.schoolIDET);
        EditText classGradeET = findViewById(R.id.classGradeET);
        EditText classIDET = findViewById(R.id.classIDET);


        String parentFirstName = edtxtFirstName.getText().toString();
        String parentLastName = edtxtLastName.getText().toString();
        String parentEmail = edtxtEmail.getText().toString();
        String parentPassword = edtxtPassword.getText().toString();
        String childID = edtxtChildID.getText().toString();
       // String parentUsername = username.getText().toString();
        //String schoolID = schoolIDET.getText().toString();
        String classGrade = classGradeET.getText().toString();
        String classID = classIDET.getText().toString();
        //IF CHILD ID DOESNT EXIST THROW ERROR
        //if db doesnt contail schoolid or class id throw message

        if(parentFirstName.isEmpty() || parentLastName.isEmpty() || parentEmail.isEmpty() || parentPassword.isEmpty() || childID.isEmpty()){
            Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        newAuth.createUserWithEmailAndPassword(parentEmail, parentPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Parent parent = new Parent(parentFirstName, parentLastName, parentEmail, childID, parentUsername);

                    FirebaseUser firebaseUser = newAuth.getCurrentUser();
                    String parentID = firebaseUser.getUid();

                    HashMap<String, Object> parentHashmap = new HashMap<>();
                    parentHashmap.put("parentFirstName", parentFirstName);
                    parentHashmap.put("parentLastName", parentLastName);
                    parentHashmap.put("parentEmail", parentEmail);
                    parentHashmap.put("email", parentEmail);
                    parentHashmap.put("parentID", parentID);
                    parentHashmap.put("schoolID", schoolID);
                    parentHashmap.put("classGrade", classGrade);
                    parentHashmap.put("classID", classID);





                    FirebaseDatabase.getInstance().getReference("Parents").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(parentHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(ParentRegisterActivity.this, EnterChildIDActivity.class);
                                    intent.putExtra("schoolID", schoolID);
                                    intent.putExtra("classGrade", classGrade);
                                    intent.putExtra("classID", classID);
                                    startActivity(intent);
                                    // enterChildIDPage();
                                }
                            });
                }else {
                    Toast.makeText(ParentRegisterActivity.this, "Authentication Failed. Please Try Again", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    /*private void enterChildIDPage(){
        Intent intent = new Intent(this, EnterChildIDActivity.class);
        startActivity(intent);

    }*/

   /* private void showMainPage(){
        Intent intent = new Intent(this, ParentMain.class);
        startActivity(intent);
        finish();
    }*/

    private void switchToLoginPage() {
        Intent intent = new Intent(this, ParentLoginActivity.class);
        startActivity(intent);
        finish();
    }
}