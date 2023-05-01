package com.example.attex.adminacademics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class AdminTestDetailsActivity extends AppCompatActivity {

    Button next;


    String[] classes = {"Junior Infants", "Senior Infants", "1st Class", "2nd Class", "3rd Class", "4th Class", "5th Class", "6th Class"};
    String [] subjects = {"English", "Irish", "Maths"};
    AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapter, adapter2;

    String classSelected;
    String subjectSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_test_details);

        FirebaseAuth auth= FirebaseAuth.getInstance();
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
                classSelected = adapterView.getItemAtPosition(i).toString();
                System.out.println(item);
                Toast.makeText(AdminTestDetailsActivity.this, "Class " + item, Toast.LENGTH_SHORT).show();

            }
        });


       autoCompleteTextView2 = findViewById(R.id.autoComplete2);
       adapter2 = new ArrayAdapter<String>(this, R.layout.list_subjects, subjects);

       autoCompleteTextView2.setAdapter(adapter2);
       autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String item = adapterView.getItemAtPosition(i).toString();
               subjectSelected = adapterView.getItemAtPosition(i).toString();
               System.out.println(item);
               Toast.makeText(AdminTestDetailsActivity.this, "Subject " + item, Toast.LENGTH_SHORT).show();

           }
       });


        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                String year = Integer.toString(currentYear);

                Intent intent = new Intent(AdminTestDetailsActivity.this, AdminCreateTestActivity.class);
                intent.putExtra("classGrade", classSelected);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("subject", subjectSelected);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });













    }


}