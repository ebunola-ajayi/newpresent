package com.example.attex.teachermain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.attex.R;
import com.google.firebase.database.DataSnapshot;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AttendanceHistoryActivity extends AppCompatActivity {

    Button dateButton;
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



    }


    private void openDialog(){

        Calendar calendar = Calendar.getInstance();
        //String currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                textView.setText(String.valueOf(day)+"-"+String.valueOf(currentMonth)+"-"+String.valueOf(currentYear));
            }
        }, currentYear, currentMonth, day);

        dialog.show();
    }


}