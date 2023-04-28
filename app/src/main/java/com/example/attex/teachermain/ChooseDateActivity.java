package com.example.attex.teachermain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.attex.R;

import java.util.Calendar;

public class ChooseDateActivity extends AppCompatActivity {

    ImageButton dateButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history);

        dateButton = findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                 }
        });


        Intent i = getIntent();
        String classID = i.getStringExtra("classID");
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");
        System.out.println(classGrade+ classID+ schoolID);

    }


    private void openDialog(){
        textView = findViewById(R.id.textView);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                textView.setText(String.valueOf(day)+"-"+String.valueOf(currentMonth)+"-"+String.valueOf(currentYear));

                String dayOfMonth = Integer.toString(datePicker.getDayOfMonth());
                String month = Integer.toString(datePicker.getMonth() + 1); //had to hardcode in +1 as Android kept subtracting the month value by 1 for some reason, (i.e, April kept appearing as 3 instead of 4)
                if(month.length() != 2){ //any month that isn't 10,11,12, should start with an 0, e.g. April should be 04
                    String start = "0";
                    month = start + month;
                }

                String year = Integer.toString(datePicker.getYear());
                String fullDate = dayOfMonth + "-" + month + "-" + year;
                nextActitvity(dayOfMonth, month, year, fullDate);
                textView.setText("Date Selected: " + fullDate);

            }
        }, currentYear, currentMonth, day);

        dialog.show();
    }

    public void nextActitvity(String dayofMonth, String month, String year, String fullDate){
        Intent i = getIntent();
        String classID = i.getStringExtra("classID");
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");

        Intent intent = new Intent(ChooseDateActivity.this, AttendanceHistoryActivity.class);
        intent.putExtra("classID", classID);
        intent.putExtra("schoolID", schoolID);
        intent.putExtra("classGrade", classGrade);
        intent.putExtra("dayofMonth", dayofMonth);
        intent.putExtra("month", month);
        intent.putExtra("year", year);
        intent.putExtra("fullDate", fullDate);
        startActivity(intent);
    }



}