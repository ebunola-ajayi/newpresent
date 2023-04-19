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

import com.example.attex.R;
import com.example.attex.teachermain.TeacherLoginActivity;
import com.example.attex.models.ModelAcademics;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
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
    Button listView;
    Button chartView;
    RecyclerView recyclerView;
    AcademicResultsAdapter adapter;
    ArrayList<ModelAcademics> gradeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_chart);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        //String userID = currentUser.getUid();


        if(currentUser == null){
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);


        Intent i2 = getIntent();
        String teacherID = i2.getStringExtra("teacherID");
        System.out.println(teacherID);

        Intent i3 = getIntent();
        String subject = i3.getStringExtra("subject");
        System.out.println(subject);

        Intent i4 = getIntent();
        String topic = i4.getStringExtra("topic");
        System.out.println(topic);

        Intent i5 = getIntent();
        String schoolID = i4.getStringExtra("schoolID");
        System.out.println(schoolID);

        Intent i6 = getIntent();
        String classGrade = i6.getStringExtra("classGrade");
        System.out.println(classGrade);

        listView = findViewById(R.id.listView);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.INVISIBLE);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gradeList = new ArrayList<>();
        adapter = new AcademicResultsAdapter(gradeList, this);
        recyclerView.setAdapter(adapter);





        ArrayList<BarEntry> barEntries = new ArrayList<>();

        //ArrayList<String> list = new ArrayList<>();
        ArrayList<Float> list = new ArrayList<>();
        ArrayList<Object> listofGrades = new ArrayList<>();



       //PieChart pieChart = findViewById(R.id.piechart);
        ArrayList<PieEntry> students = new ArrayList<>();

        ArrayList<String> names = new ArrayList<>();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AcademicRecord").child(schoolID).child(classGrade).child(teacherID).child(subject).child(topic);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // for(int i=0; i< snapshot.getChildrenCount(); i++){
                //add for each loop under this one7
                String grade = null;
                String name;
                String studentID;
                //for(int i=0; i< snapshot.getChildrenCount(); i++) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ModelAcademics subjectGrade = dataSnapshot.getValue(ModelAcademics.class);

                        grade = subjectGrade.getGrade();
                        name = subjectGrade.getName();
                        studentID = subjectGrade.getStudentID();
                        int grade2 = Integer.parseInt(grade);

                        float grade3 = Float.parseFloat(grade);

                        //int gradeAmount = grade.
                    /*gradeList.add(subjectGrade);
                    System.out.println(gradeList);*/

                        list.add(grade3);
                        names.add(name);
                       // listofGrades.add(grade2, studentID);


                        //BarEntry barEntry = new BarEntry();






                        barChart = findViewById(R.id.barchart);


                      /*  ArrayList<BarEntry> barEntries = new ArrayList<>();
                        barEntries.add(new BarEntry(8f, list.get(1)));
                        barEntries.add(new BarEntry(2f, list.get(2)));
                        barEntries.add(new BarEntry(5f, list.get(3)));


                        BarDataSet barDataSet = new BarDataSet(barEntries, "Students");


                        ArrayList<String> labels = new ArrayList<>();
                        labels.add(studentID);

                        BarData data = new BarData((IBarDataSet) labels, barDataSet);
                        barChart.setData(data);
                        barChart.getDescription().setText("Grades");
                        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                        barChart.animateY(5000);*/


                        chartView = findViewById(R.id.chartView);
                        chartView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                barChart.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                            }
                        });

                        //for RCV
                        gradeList.add(subjectGrade);
                    }
                    adapter.notifyDataSetChanged();

                System.out.println(list);
                    //need to get the values inside 'list' and insert them inside barEntry object






                //ArrayList<String> list2 = new ArrayList<>();



                /*ArrayList<PieEntry> pieEntries = new ArrayList<>();
                for(Object num : listofGrades){
                    for(int i=0; i<listofGrades.size(); i++){
                        pieEntries.add(new PieEntry((Float) listofGrades.get(i), "Student"));
                        System.out.println("Pie Entries: " + pieEntries);
                    }

                }*/

                ArrayList<PieEntry> pieEntries = new ArrayList<>();

                ArrayList<BarEntry> barEntries = new ArrayList<>();
                for(int i=0; i<list.size(); i++){

                    float hello = list.get(i);
                    BarEntry barEntry = null;
                    barEntry = new BarEntry(i, list.get(i));
                    barEntries.add(barEntry);

                    //for(float num : list) {
                        //int i;

                        //PieEntry pieEntry = null;
                        //for (i = 0; i < list.size(); i++) {
                            // int i = 0;

                            //barEntry = new BarEntry(i, num);
                            //System.out.println(barEntries);
                            //add for loop after above statement
                            //barEntries.add(new BarEntry());
                            //pieEntry = new PieEntry(i, num);
                            //pieEntries.add(pieEntry);
                      //  }





                    }
               // }

                float totalAverage = 0;
                for (int i=0; i<list.size(); i++){
                    totalAverage = totalAverage + list.get(i);
                }

                float avg = totalAverage / list.size();
                System.out.println(avg);
                String avgString = Float.toString(avg);

                TextView averageTV = findViewById(R.id.averageTV);
                averageTV.setText("Average: " + avgString);



                /*1. Need to convert string to float (for grades)

                * */





                /*for (int i=0; i<list.size(); i++){
                    //may need to add for each loop here

                    float value = (float) (i*10.0);

                    BarEntry barEntry = new BarEntry(i, value);
                    barEntries.add(barEntry);

                }*/
                //System.out.println(barEntries);


                BarDataSet barDataSet = new BarDataSet(barEntries, "Grades");

                //for (int i=0; i<names.size(); i++){
                //    BarData data = new BarData(names.get(i), barDataSet);
                //}




                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                barDataSet.setDrawValues(false);
                barChart.setData(new BarData(barDataSet));
                barChart.animateY(5000);
                barChart.getDescription().setText("Grades Per Student");
                barChart.getDescription().setTextColor(Color.BLUE);

               /* PieDataSet pieDataSet = new PieDataSet(pieEntries, "Grades");
                pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);


                pieDataSet.setDrawValues(false);
                pieChart.setData(new PieData(pieDataSet));
                pieChart.animateXY(5000, 5000);
                pieChart.getDescription().setEnabled(false);*/









               // }
                //list.add(grade);


                // String grade = subjectGrade.getGrade();
                // int grade = subjectGrade.getGrade();
                // float grade2 = Float.parseFloat(grade);
                // BarEntry barEntry = new BarEntry(i, grade2);

                // barEntries.add(barEntry);
                //gradeList.add(grade);
                // System.out.println(grade2);
                // }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

/*        BarDataSet barDataSet = new BarDataSet(barEntries, "Grades");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barDataSet.setDrawValues(false);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(5000);
        barChart.getDescription().setText("Grade Bar Chart");
        barChart.getDescription().setTextColor(Color.BLUE);*/





    }
}