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
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAttendance;
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

public class StudentAttendanceActivity extends AppCompatActivity {


    String[] months = {"January", "February", "March", "April", "May", "June", "September", "October", "November", "December"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    //lists for present values and absent values
    ArrayList<ModelAttendance> presentList;
    ArrayList<ModelAttendance> absentList;


    RecyclerView recyclerView;
    TextView studentNameTV;
    String year;
    StudenceAttendanceAdapter attendanceAdapter;
    ArrayList<ModelAttendance> attendanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        //talk about the problems it solves
        //scenario of problems it can solve
        //what system

        Intent i = getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println("HEY " + studentID);
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String firstName = i.getStringExtra("firstName");
        String lastName = i.getStringExtra("lastName");
        String studentFullName = i.getStringExtra("studentFullName");

        studentNameTV = findViewById(R.id.studentName);
        studentNameTV.setText(studentFullName);

        //RCV
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceList = new ArrayList<>();
        attendanceAdapter = new StudenceAttendanceAdapter(attendanceList, this);
        recyclerView.setAdapter(attendanceAdapter);


        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        year = Integer.toString(currentYear);


        presentList = new ArrayList<>();
        absentList = new ArrayList<>();



        autoCompleteTextView = findViewById(R.id.autoCompleteMonth);
        adapter = new ArrayAdapter<String>(this, R.layout.list_months, months);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String monthSelected = adapterView.getItemAtPosition(i).toString();
                System.out.println(monthSelected);

                //make recycler view of attendance history visible for month selected
                recyclerView.setVisibility(View.VISIBLE);

                //if the month selected is september - december, subtract the year by 1, as these months are in the previous year
                if(monthSelected.equals("September") || monthSelected.equals("October") || monthSelected.equals("November") || monthSelected.equals("December")){
                    int last_Year = currentYear -1;
                    String lastYear = Integer.toString(last_Year);
                     year = lastYear;
                    System.out.println("Last Year: " + year);
                } else {

                    //fetching and populating data for the rcv
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AttendanceRecord").child(schoolID).child(classGrade).child(classID).child(studentID).child(year).child(monthSelected);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    ModelAttendance attendance = dataSnapshot.getValue(ModelAttendance.class);
                                    attendanceList.add(attendance);
                                }
                                attendanceAdapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(StudentAttendanceActivity.this, "No Data For Month Selected", Toast.LENGTH_SHORT).show();
                            }


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    }); //close addValueEventListenser


                    //query for fetching data where attendance = present
                    Query query = FirebaseDatabase.getInstance().getReference("AttendanceRecord").child(schoolID).child(classGrade).child(classID).child(studentID).child(year).child(monthSelected)
                            .orderByChild("attendance")
                            .equalTo("Present");

                    //declare value event listener
                     ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            presentList.clear();
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    ModelAttendance present  = snapshot.getValue(ModelAttendance.class);
                                    presentList.add(present); //add these values to the present list
                                }
                            } else {
                                System.out.println("No Present Days");
                                Toast.makeText(StudentAttendanceActivity.this, "No Present Days", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    };
                    //add this to query
                    query.addListenerForSingleValueEvent(valueEventListener);


                    //query for fetching data where attendance = absent
                    Query query2 = FirebaseDatabase.getInstance().getReference("AttendanceRecord").child(schoolID).child(classGrade).child(classID).child(studentID).child(year).child(monthSelected)
                            .orderByChild("attendance")
                            .equalTo("Absent");

                    //declare value event listener
                    ValueEventListener valueEventListener2 = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            absentList.clear();
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    ModelAttendance absent  = snapshot.getValue(ModelAttendance.class);
                                    absentList.add(absent);
                                    calculateAverage(presentList.size(), absentList.size());
                                }
                            } else {
                                System.out.println("No Absent Days");
                                Toast.makeText(StudentAttendanceActivity.this, "No Absent Days", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };

                    //add valueEventLister2 to query2
                    query2.addListenerForSingleValueEvent(valueEventListener2);

                }

            }


        });

    }



    private void calculateAverage(int present, int absent) {
        double a = absent;
        double p = present;

        double totalAtt = a + p;
        double pPercent = p / totalAtt;
        double pPercent2 = pPercent * 100;
        System.out.println(present + "+ " + absent);
        System.out.println(pPercent2 + "%");
        Toast.makeText(this, "Attendance Average For This Month: " + pPercent2 + "%", Toast.LENGTH_SHORT).show();
    }






}