package com.example.attex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.attex.adminmain.AdminLoginActivity;
import com.example.attex.parentmain.ParentLoginActivity;
import com.example.attex.teachermain.TeacherLoginActivity;

public class InitialLoginActivity extends AppCompatActivity {

    ImageView teacherButton;
    ImageView parentButton;
    ImageView adminButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_login);



        teacherButton = findViewById(R.id.teacherBtn);
        parentButton = findViewById(R.id.parentBtn);

        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherLogin();
            }
        });

        parentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentLogin();
            }
        });

        adminButton = findViewById(R.id.adminBtn);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitialLoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void teacherLogin() {
        Intent intent = new Intent(this, TeacherLoginActivity.class);
        startActivity(intent);
    }

    private void parentLogin() {
        Intent intent = new Intent(this, ParentLoginActivity.class);
        startActivity(intent);
    }


}