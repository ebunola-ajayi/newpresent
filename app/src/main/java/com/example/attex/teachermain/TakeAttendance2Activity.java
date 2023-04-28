package com.example.attex.teachermain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class TakeAttendance2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    TakeAttendance2Adapter adapter;
    ArrayList<ModelAttendance> attendanceList;

    Button submit;
    TextView dateET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance2);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        String date = currentDate.format(todayDate);


        //current month and year
        Calendar calendar = Calendar.getInstance();
        String currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        String year = Integer.toString(currentYear);
        System.out.println(currentMonth + year);

        dateET = findViewById(R.id.date);
        dateET.setText("ATTENDANCE - " + date);

        if(date.contains("/") || !date.contains("-")){
            Toast.makeText(this, "Date Format Must Be: DD-MM-YYYY", Toast.LENGTH_SHORT).show();

        }

        //intent details
        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String classID = i.getStringExtra("classID");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StudentDetails").child(schoolID).child(classGrade).child(classID);

        //RCV details
        attendanceList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TakeAttendance2Adapter(attendanceList, this);
        recyclerView.setAdapter(adapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelAttendance attendanceModel = dataSnapshot.getValue(ModelAttendance.class);
                    attendanceList.add(attendanceModel);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Attendance").child(schoolID).child(classGrade).child(classID).child(year).child(currentMonth).child(date);
                DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("AttendanceRecord").child(schoolID).child(classGrade).child(classID);

                HashMap<String, Object> attendanceHashMap = new HashMap<>();
                HashMap<String, Object> studentAttHashMap = new HashMap<>();

                for(int i=0; i<attendanceList.size(); i++){
                    String firstName = attendanceList.get(i).getFirstName();
                    String lastName = attendanceList.get(i).getLastName();
                    String studentID = attendanceList.get(i).getStudentID();
                    String attendance = attendanceList.get(i).getAttendance();

                    ModelAttendance modelAttendance = new ModelAttendance(firstName, lastName, studentID, date, attendance, classID);

                    attendanceHashMap.clear();
                    attendanceHashMap.put("studentID", modelAttendance.getStudentID());
                    attendanceHashMap.put("attendance", modelAttendance.getAttendance());
                    attendanceHashMap.put("firstName", modelAttendance.getFirstName());
                    attendanceHashMap.put("lastName", modelAttendance.getLastName());
                    attendanceHashMap.put("date", date);
                    attendanceHashMap.put("classID", classID);

                    studentAttHashMap.clear();
                    studentAttHashMap.put("attendance", modelAttendance.getAttendance());
                    studentAttHashMap.put("date", date);
                    studentAttHashMap.put(date, modelAttendance.getAttendance());


                    Toast.makeText(TakeAttendance2Activity.this, "Attendance Saved", Toast.LENGTH_SHORT).show();

                    //attendance for the whole class
                    ref.child(studentID).setValue(attendanceHashMap);
                    //attendance record for each individual student
                    ref2.child(studentID).child(year).child(currentMonth).child(date).setValue(studentAttHashMap);

                }

            }
        });

    }
}