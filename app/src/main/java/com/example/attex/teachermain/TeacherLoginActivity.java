package com.example.attex.teachermain;


//DO EMAIL NOT USERNAME
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

public class TeacherLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //public static final String EXTRA_NAME2 = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        mAuth = FirebaseAuth.getInstance();
        //if user exists or not
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });

        TextView switchToRegister = findViewById(R.id.registerTV);
        switchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPage();
            }
        });
    }

    private void authenticateUser(){
        EditText emailET = findViewById(R.id.emailET);
        EditText passwordET = findViewById(R.id.passwordET);
        //EditText usernameET = findViewById(R.id.usernameET);

       // String username = usernameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

       /* if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }*/
        if(email.isEmpty()){
            emailET.requestFocus();
            return;
        } else if(password.isEmpty()){
            passwordET.requestFocus();
            return;
        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //showMainActivity();
                                Intent intent = new Intent(TeacherLoginActivity.this, TeacherMainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(TeacherLoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }

    private void registerPage(){
        Intent intent = new Intent(this, TeacherRegisterActivity.class);
        startActivity(intent);
        finish();
    }
}