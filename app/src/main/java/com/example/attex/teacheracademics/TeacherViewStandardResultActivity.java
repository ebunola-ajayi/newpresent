package com.example.attex.teacheracademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelStandardExam;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class TeacherViewStandardResultActivity extends AppCompatActivity {

    Button listView, chartView;
    BarChart barChart;
    TextView averageTV;

    RecyclerView recyclerView;
    TeacherStandardResultAdapter adapter;
    ArrayList<ModelStandardExam> resultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_standard_result);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }



        Intent i = getIntent();
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        String classID = i.getStringExtra("classID");
        String subject = i.getStringExtra("subject");

        //rcv details
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultList = new ArrayList<>();
        adapter = new TeacherStandardResultAdapter(resultList, this);
        recyclerView.setAdapter(adapter);

        barChart = findViewById(R.id.barchart);
        listView = findViewById(R.id.listView);
        chartView = findViewById(R.id.chartView);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
            }
        });

        ArrayList<Float> gradeListChart = new ArrayList<>();
        ArrayList<String> studentNames = new ArrayList<>();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StandardExamResults").child(schoolID).child(classGrade).child(classID).child(subject);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                        ModelStandardExam result = dataSnapshot.getValue(ModelStandardExam.class);

                        //populating grade list for chart
                        String grade = result.getGrade();
                        float gradeFloat = Float.parseFloat(grade);
                        gradeListChart.add(gradeFloat);

                        //populating student name list for chart (x axis)
                        String studentName = result.getStudentName();
                        studentNames.add(studentName);

                        //populating list for rcv
                        resultList.add(result);
                    }
                    adapter.notifyDataSetChanged();




                    //calculating average for standard exam grades
                    float totalAverage = 0;
                    for (int i=0; i<gradeListChart.size(); i++){
                        totalAverage = totalAverage + gradeListChart.get(i);
                    }

                    float averageFloat = totalAverage / gradeListChart.size();
                    String avgString = Float.toString(averageFloat);

                    //setting average to textview
                    averageTV = findViewById(R.id.averageTV);
                    averageTV.setText("Class Average " + avgString);

                    //create bar entries list and populate it
                    ArrayList<BarEntry> barEntries = new ArrayList<>();
                    for(int i=0; i<gradeListChart.size(); i++) {

                        BarEntry barEntry = new BarEntry(i, gradeListChart.get(i));
                        barEntries.add(barEntry);
                    }

                    BarDataSet barDataSet = new BarDataSet(barEntries, "Grades");
                    Description description = new Description();
                    description.setText("Students");
                    barChart.setDescription(description);

                    BarData bardData = new BarData(barDataSet);
                    barChart.setData(bardData);

                    //setting x axis to reflect student names
                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(studentNames));

                    //setting positions of names
                    xAxis.setPosition(XAxis.XAxisPosition.TOP);
                    xAxis.setDrawGridLines(false);
                    xAxis.setDrawAxisLine(false);
                    xAxis.setGranularity(1f);
                    xAxis.setLabelCount(studentNames.size());
                    xAxis.setLabelRotationAngle(270);


                    barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                    barDataSet.setDrawValues(false);
                    barChart.animateY(5000);
                    barChart.getDescription().setText("Grades Per Student");
                    barChart.getDescription().setTextColor(Color.BLUE);


                    chartView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            barChart.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    });
                } else {
                    Toast.makeText(TeacherViewStandardResultActivity.this, "No Result Recorded Yet!", Toast.LENGTH_SHORT).show();

                    listView.setVisibility(View.GONE);
                    chartView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}