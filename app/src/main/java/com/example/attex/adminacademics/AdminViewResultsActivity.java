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

import com.example.attex.InitialLoginActivity;
import com.example.attex.models.ModelStandardExam;
import com.example.attex.R;
import com.example.attex.teacheracademics.TeacherStandardResultAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
    ArrayList<Float> list = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

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


        recyclerView = findViewById(R.id.recyclerView);

        listView = findViewById(R.id.listView);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.INVISIBLE);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StandardExamResults").child(schoolID).child(classGrade).child(classID);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("StandardExamResults").child("IDC133").child("2nd Class").child("FALANGE2023");

        resultList = new ArrayList<>();
        adapter = new TeacherStandardResultAdapter(resultList, this);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String grade = null;
                String studentName;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelStandardExam result = dataSnapshot.getValue(ModelStandardExam.class);

                    grade = result.getGrade();
                    studentName = result.getStudentName();

                    float grade3 = Float.parseFloat(grade);





                    //for bar chart
                    list.add(grade3);
                    names.add(studentName);

                    barChart = findViewById(R.id.barchart);
                    chartView = findViewById(R.id.chartView);
                    chartView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            barChart.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);
                        }
                    });

                    //for rcv
                    resultList.add(result);
                }
                adapter.notifyDataSetChanged();


                ArrayList<BarEntry> barEntries = new ArrayList<>();
                for(int i=0; i<list.size(); i++) {

                    float hello = list.get(i);
                    BarEntry barEntry = null;
                    barEntry = new BarEntry(i, list.get(i));
                    barEntries.add(barEntry);
                }

                float totalAverage = 0;
                for (int i=0; i<list.size(); i++){
                    totalAverage = totalAverage + list.get(i);
                }

                float avg = totalAverage / list.size();
                System.out.println(avg);

                averageTV = findViewById(R.id.averageTV);

                String avgString = Float.toString(avg);
                averageTV.setText("Class Average " + avgString);



                BarDataSet barDataSet = new BarDataSet(barEntries, "Grades");

                barDataSet.setDrawValues(false);
                barChart.setData(new BarData(barDataSet));
                barChart.animateY(5000);
                barChart.getDescription().setText("Grades Per Student");
                barChart.getDescription().setTextColor(Color.BLUE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}