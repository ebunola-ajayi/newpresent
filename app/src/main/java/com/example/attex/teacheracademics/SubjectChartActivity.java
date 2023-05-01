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

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAcademics;
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

public class SubjectChartActivity extends AppCompatActivity {


    BarChart barChart;
    Button listView, chartView;
    RecyclerView recyclerView;
    AcademicResultsAdapter adapter;
    ArrayList<ModelAcademics> gradeListRCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_chart);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        //intents
        Intent i2 = getIntent();
        String teacherID = i2.getStringExtra("teacherID");
        String subject = i2.getStringExtra("subject");
        String topic = i2.getStringExtra("topic");
        String schoolID = i2.getStringExtra("schoolID");
        String classGrade = i2.getStringExtra("classGrade");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gradeListRCV = new ArrayList<>();
        adapter = new AcademicResultsAdapter(gradeListRCV, this);
        recyclerView.setAdapter(adapter);


        //if user chooses list view, show RCV
        listView = findViewById(R.id.listView);
        barChart = findViewById(R.id.barchart);


        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
            }
        });


        ArrayList<Float> gradeListChart = new ArrayList<>();  //this list is for poulating the chart
        ArrayList<String> studentNames = new ArrayList<>(); // this list is for poulating the list of names


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AcademicRecord").child(schoolID).child(classGrade).child(teacherID).child(subject).child(topic);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ModelAcademics subjectGrade = dataSnapshot.getValue(ModelAcademics.class);
                        String studentGrade = subjectGrade.getGrade();
                        float studentGradeFloat = Float.parseFloat(studentGrade);  //converting grade value to float, for chart
                        gradeListChart.add(studentGradeFloat);   //add student grade values to gradeListChart


                        //populating studentNames list for the chart
                        String name = subjectGrade.getName();
                        studentNames.add(name); //add student names to studentNamesList

                        //populating gradeListRCV for RCV
                        gradeListRCV.add(subjectGrade);
                    }
                    adapter.notifyDataSetChanged();





                //calculating average for the topic
                float totalAverage = 0;
                for (int i=0; i<gradeListChart.size(); i++){
                    totalAverage = totalAverage + gradeListChart.get(i);
                }

                //calculate average
                float averageFloat = totalAverage / gradeListChart.size();
                float rounded = (float) Math.round(averageFloat);
                System.out.println(rounded);

                String avgString = Float.toString(rounded);


                //set average to textview
                TextView averageTV = findViewById(R.id.averageTV);
                averageTV.setText("CLASS AVERAGE: " + avgString + "%");


                //1 create barEntries list, this gives the value for each bar
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                for(int i=0; i<gradeListChart.size(); i++){

                    BarEntry barEntry = new BarEntry(i, gradeListChart.get(i));
                    barEntries.add(barEntry);
                }


                //2 creating bardataset object
                BarDataSet barDataSet = new BarDataSet(barEntries, "Grades");
                Description description = new Description();
                description.setText("Students");
                barChart.setDescription(description);

                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);

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


                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                barDataSet.setDrawValues(false);
                barChart.animateY(5000);
                barChart.getDescription().setText("Grades Per Student");
                barChart.getDescription().setTextColor(Color.BLUE);


                //if user chooses chart view
                chartView = findViewById(R.id.chartView);
                chartView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        barChart.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}