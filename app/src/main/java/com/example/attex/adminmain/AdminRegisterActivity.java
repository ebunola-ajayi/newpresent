package com.example.attex.adminmain;

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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminRegisterActivity extends AppCompatActivity {


    EditText schoolNameET, schoolIDET, adminEmailET, passwordET;
    Button regButton;
    TextView loginLink;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        regButton = findViewById(R.id.regButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAdmin();
            }
        });

        loginLink = findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage();
            }
        });





    }

    public void registerAdmin(){
        schoolNameET = findViewById(R.id.schoolNameET);
        schoolIDET = findViewById(R.id.schoolID);
        adminEmailET = findViewById(R.id.adminEmailET);
        passwordET = findViewById(R.id.passwordET);
        regButton = findViewById(R.id.regButton);
        loginLink = findViewById(R.id.loginLink);



        String schoolName = schoolNameET.getText().toString();
        String schoolID = schoolIDET.getText().toString();
        String adminEmail = adminEmailET.getText().toString();
        String password = passwordET.getText().toString();

        if(schoolName.isEmpty() || schoolID.isEmpty() || adminEmail.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(adminEmail, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    HashMap<String, Object> adminHashmap = new HashMap<>();
                    adminHashmap.put("schoolName", schoolName);
                    adminHashmap.put("schoolID", schoolID);
                    adminHashmap.put("adminEmail", adminEmail);
                    adminHashmap.put("adminID", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    FirebaseDatabase.getInstance().getReference("Admin").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(adminHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(AdminRegisterActivity.this, AdminMainActivity.class);
                                    // intent.putExtra(EXTRA_NAME, tUsername);
                                    startActivity(intent);
                                    finish();
                                }
                            });




                } else {
                    Toast.makeText(AdminRegisterActivity.this, "Registration Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginPage(){
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
        finish();
    }

}