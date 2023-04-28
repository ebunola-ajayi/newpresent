package com.example.attex.teachermain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.attex.R;
import com.example.attex.models.ModelLearningDisability;
import com.example.attex.models.ModelNote;

import java.util.ArrayList;

public class LearningDisabilityDetailsActivity extends AppCompatActivity {

    TextView titleTV, shortDescTV, linkTV;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_disability_details);

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String shortDesc = i.getStringExtra("shortDesc");
        ArrayList<String> symptoms = i.getStringArrayListExtra("symptoms");

        System.out.println(title);
        System.out.println(shortDesc);
        System.out.println(symptoms);

        listView = (ListView) findViewById(R.id.listView);
        titleTV = findViewById(R.id.titleTV);
        titleTV.setText(title);

        shortDescTV = findViewById(R.id.shortDescTV);
        shortDescTV.setText(shortDesc);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, R.layout.layout_symptoms, R.id.symptomTV, symptoms);
        listView.setAdapter(arrayAdapter);



        linkTV = findViewById(R.id.linkTV);
        linkTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.ldrfa.org/the-top-5-most-common-learning-disabilities-their-symptoms/#:~:text=The%20top%20five%20most%20common,of%20medical%20and%20educational%20assessments.");

            }
        });

    }

    private void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}