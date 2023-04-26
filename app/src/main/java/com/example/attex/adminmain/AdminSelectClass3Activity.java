package com.example.attex.adminmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminSelectClass3Activity extends AppCompatActivity {

    String[] classes = {"Junior Infants", "Senior Infants", "1st Class", "2nd Class", "3rd Class", "4th Class", "5th Class", "6th Class"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_class3);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");

        autoCompleteTextView = findViewById(R.id.autoComplete);
        adapter = new ArrayAdapter<String>(this, R.layout.list_classes, classes);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                System.out.println(item);
                Toast.makeText(AdminSelectClass3Activity.this, "Class " + item, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminSelectClass3Activity.this, AdminListClassesActivity.class);
                intent.putExtra("classGrade", item);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);

            }
        });



    }
}