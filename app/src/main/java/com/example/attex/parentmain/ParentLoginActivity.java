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
import com.example.attex.models.ModelParent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentLoginActivity extends AppCompatActivity {
    private FirebaseAuth newAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        newAuth = FirebaseAuth.getInstance();
        //if user exists or not
        if (newAuth.getCurrentUser() != null){
            finish();
            return;
        }

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateParent();
            }
        });

        TextView registerTV = findViewById(R.id.registerTV);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //registerTV();
                Intent intent = new Intent(ParentLoginActivity.this, ParentEnterSchoolIDActivity.class);
                startActivity(intent);
            }
        });

    }

    private void authenticateParent() {
        EditText emailEdTxt = findViewById(R.id.emailEdTxt);
        EditText passwordEdTxt = findViewById(R.id.passwordEdTxt);

        String email = emailEdTxt.getText().toString();
        String password = passwordEdTxt.getText().toString();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        newAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //showMainPage();
                            //db ref, get schoolID
                            FirebaseAuth auth = FirebaseAuth.getInstance();
                            FirebaseUser currentUser = auth.getCurrentUser();
                            String id = currentUser.getUid();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Parents").child(id);

                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    ModelParent parent = snapshot.getValue(ModelParent.class);

                                    String schoolID = parent.getSchoolID();
                                    String classGrade = parent.getClassGrade();
                                    String classID = parent.getClassID();

                                    Intent intent = new Intent(ParentLoginActivity.this, EnterChildIDActivity.class);
                                    intent.putExtra("schoolID", schoolID);
                                    intent.putExtra("classGrade", classGrade);
                                    intent.putExtra("classID", classID);
                                    startActivity(intent);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        } else {
                            Toast.makeText(ParentLoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}