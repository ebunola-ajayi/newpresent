package com.example.attex.adminacademics;

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
import com.example.attex.models.ModelStandardExam;
import com.example.attex.R;
import com.example.attex.teacheracademics.TeacherStandardResultAdapter;
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

public class AdminViewResultsActivity extends AppCompatActivity {

    Button listView, chartView;
    BarChart barChart;
    TextView averageTV;

    RecyclerView recyclerView;
    TeacherStandardResultAdapter adapter;
    ArrayList<ModelStandardExam> resultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_results);

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


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultList = new ArrayList<>();
        adapter = new TeacherStandardResultAdapter(resultList, this);
        recyclerView.setAdapter(adapter);


        averageTV = findViewById(R.id.averageTV);
        barChart = findViewById(R.id.barchart);
        listView = findViewById(R.id.listView);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
            }
        });

        //lists for bar chart
        ArrayList<Float> gradeListChart = new ArrayList<>();
        ArrayList<String> studentNamesChart = new ArrayList<>();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StandardExamResults").child(schoolID).child(classGrade).child(classID).child(subject);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelStandardExam result = dataSnapshot.getValue(ModelStandardExam.class);

                    //populate student grades chart
                    String grade = result.getGrade();
                    float studentGradeFloat = Float.parseFloat(grade);
                    gradeListChart.add(studentGradeFloat);

                    //populate student names chart
                    String studentName = result.getStudentName();
                    studentNamesChart.add(studentName);


                    chartView = findViewById(R.id.chartView);
                    chartView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            barChart.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    });

                    //populating list for RCV
                    resultList.add(result);
                }

                adapter.notifyDataSetChanged();


                //calculating average
                float totalAverage = 0;
                for (int i = 0; i < gradeListChart.size(); i++) {
                    totalAverage = totalAverage + gradeListChart.get(i);
                }
                float avg = totalAverage / gradeListChart.size();
                String avgString = Float.toString(avg);

                averageTV.setText("Class Average " + avgString);

                //create barEntries list
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                for (int i = 0; i < gradeListChart.size(); i++) {
                    BarEntry barEntry = new BarEntry(i, gradeListChart.get(i));
                    barEntries.add(barEntry);
                }


                //create bardataset object
                BarDataSet barDataSet = new BarDataSet(barEntries, "Grades");
                Description description = new Description();
                description.setText("Students");
                barChart.setDescription(description);

                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);

                //setting x axis to sgow student names
                XAxis xAxis = barChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(studentNamesChart));

                //setting the positions of names
                xAxis.setPosition(XAxis.XAxisPosition.TOP);
                xAxis.setDrawGridLines(false);
                xAxis.setDrawAxisLine(false);
                xAxis.setGranularity(1f);
                xAxis.setLabelCount(studentNamesChart.size());
                xAxis.setLabelRotationAngle(270);


                barDataSet.setDrawValues(false);
                barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                barChart.setData(new BarData(barDataSet));
                barChart.animateY(5000);
                barChart.getDescription().setText("Grades Per Student");
                barChart.getDescription().setTextColor(Color.BLUE);

            }else {
                    Toast.makeText(AdminViewResultsActivity.this, "No Grades Recorded", Toast.LENGTH_SHORT).show();
            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}