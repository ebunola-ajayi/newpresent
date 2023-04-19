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
import com.example.attex.adminacademics.AdminRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {


    EditText schoolNameET;
    EditText adminEmailET;
    EditText passwordET;
    Button loginButton;
    TextView registerLink;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth = FirebaseAuth.getInstance();
        //if user exists or not
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateAdmin();
            }
        });

        registerLink = findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPage();
            }
        });

    }

    private void authenticateAdmin(){
        schoolNameET = findViewById(R.id.schoolName);
        adminEmailET = findViewById(R.id.adminEmail);
        passwordET = findViewById(R.id.password);

        String schoolName = schoolNameET.getText().toString();
        String adminEmail = adminEmailET.getText().toString();
        String password = passwordET.getText().toString();

        if(schoolName.isEmpty() || adminEmail.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(adminEmail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(AdminLoginActivity.this, AdminMainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AdminLoginActivity.this, "Login Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void registerPage(){
        Intent intent = new Intent(this, AdminRegisterActivity.class);
        startActivity(intent);
        finish();
    }
}