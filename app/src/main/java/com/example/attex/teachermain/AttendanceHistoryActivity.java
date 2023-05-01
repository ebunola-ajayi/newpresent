package com.example.attex.teachermain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAttendance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AttendanceHistoryActivity extends AppCompatActivity {

    String stringMonth;
    RecyclerView recyclerView;
    AttendanceHistoryAdapter adapter;
    ArrayList<ModelAttendance> attendanceList;
    TextView dateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history2);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent i = getIntent();
        String classID = i.getStringExtra("classID");
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String dayOfMonth = i.getStringExtra("dayOfMonth");
        String month = i.getStringExtra("month");
        String year = i.getStringExtra("year");
        String fullDate = i.getStringExtra("fullDate");

        dateTV = findViewById(R.id.dateTV);
        dateTV.setText(fullDate);

        //finding the value for the stringMonth variable
        if(month.equals("01")){
            stringMonth = "January";
        }else if(month.equals("02")){
            stringMonth = "February";
        }else if(month.equals("03")){
            stringMonth = "March";
        }else if(month.equals("04")){
            stringMonth = "April";
        }else if(month.equals("05")){
            stringMonth = "May";
        }else if(month.equals("06")){
            stringMonth = "June";
        }else if(month.equals("07")){
            stringMonth = "July";
        }else if(month.equals("08")){
            stringMonth = "August";
        }else if(month.equals("09")){
            stringMonth = "September";
        }else if(month.equals("10")){
            stringMonth = "October";
        }else if(month.equals("11")){
            stringMonth = "November";
        }else if(month.equals("12")){
            stringMonth = "December";
        }

        //define variables for RCV
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceList = new ArrayList<>();
        adapter = new AttendanceHistoryAdapter(attendanceList, this);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance").child(schoolID).child(classGrade).child(classID).child(year).child(stringMonth).child(fullDate);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelAttendance attendance = dataSnapshot.getValue(ModelAttendance.class);
                    attendanceList.add(attendance);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}