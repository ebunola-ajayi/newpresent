package com.example.attex.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelNote;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.models.ModelAttendance;
import com.example.attex.teachermain.TeacherNotesAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class StudentAttendanceActivity extends AppCompatActivity {


    String[] months = {"January", "February", "March", "April", "May", "June", "September", "October", "November", "December"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;


    RecyclerView recyclerView;
    TextView studentName;
    String year;
    StudenceAttendanceAdapter attendanceAdapter;
    ArrayList<ModelAttendance> attendanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println("HEY " + studentID);
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String studentFullName = i.getStringExtra("studentName");

        studentName = findViewById(R.id.studentName);
        studentName.setText(studentFullName);

        //RCV
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceList = new ArrayList<>();
        attendanceAdapter = new StudenceAttendanceAdapter(attendanceList, this);
        recyclerView.setAdapter(attendanceAdapter);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
       // int lastYear = calendar.get(Calendar.YEAR - 1);

        year = Integer.toString(currentYear);
        //int lastYear =




        autoCompleteTextView = findViewById(R.id.autoCompleteMonth);
        adapter = new ArrayAdapter<String>(this, R.layout.list_months, months);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String monthSelected = adapterView.getItemAtPosition(i).toString();
                System.out.println(monthSelected);

                recyclerView.setVisibility(View.VISIBLE);
                if(monthSelected.equals("September") || monthSelected.equals("October") || monthSelected.equals("November") || monthSelected.equals("December")){
                    int last_Year = currentYear -1;
                    String lastYear = Integer.toString(last_Year);
                     year = lastYear;
                    System.out.println("Last Year: " + year);
                } else {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AttendanceRecord").child(schoolID).child(classGrade).child(classID).child(studentID).child(year).child(monthSelected);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                ModelAttendance attendance = dataSnapshot.getValue(ModelAttendance.class);
                                attendanceList.add(attendance);
                                System.out.println(attendance);

                            }
                            attendanceAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                //if selected month = Sep || Oct || Nov || Dec : current year -1;

               /* recyclerView.setVisibility(View.VISIBLE);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AttendanceRecord").child(schoolID).child(classGrade).child(classID).child(studentID).child(year).child(monthSelected);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                      for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                          ModelAttendance attendance = dataSnapshot.getValue(ModelAttendance.class);
                          attendanceList.add(attendance);
                          System.out.println(attendance);

                      }
                      attendanceAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/





            }
        });



    }


   /* public void blah(String schoolID, String classGrade, String classID, String studentID, String monthSelected){
        Calendar calendar = Calendar.getInstance();
        String currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        String year = Integer.toString(currentYear);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AttendanceRecord").child(schoolID).child(classGrade).child(classID).child(studentID).child(year).child(monthSelected);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelAttendance attendance = snapshot.getValue(ModelAttendance.class);
                    attendanceList.add(attendance);
                    System.out.println(attendance);

               // }
                attendanceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/



}