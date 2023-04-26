package com.example.attex.teachermain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.attex.R;
import com.example.attex.models.ModelLearningDisability;
import com.example.attex.models.ModelNote;

import java.util.ArrayList;

public class LearningDisabilityListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LearningDisabilityListAdapter adapter;
    ArrayList<ModelLearningDisability> learnList; // for adapter
    ArrayList<String> dyslexiaSymptoms, dysgraphiaSymptoms, dyscalculiaSymptoms, dyspraxiaSymptoms, adhdSymptoms; //for model


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_disability_list);

        learnList = new ArrayList<>();
        dyslexiaSymptoms = new ArrayList<>();
        dysgraphiaSymptoms = new ArrayList<>();
        dyscalculiaSymptoms = new ArrayList<>();
        dyspraxiaSymptoms = new ArrayList<>();
        adhdSymptoms = new ArrayList<>();


        //setLearnList();
        // dyslexia symptoms
        dyslexiaSymptoms.add("one");
        dyslexiaSymptoms.add("two");
        dyslexiaSymptoms.add("three");

        //dysgraphia Symptoms
        dysgraphiaSymptoms.add("four");
        dysgraphiaSymptoms.add("five");
        dysgraphiaSymptoms.add("six");

        //dyscalculia Symptoms
        dyscalculiaSymptoms.add("seven");
        dyscalculiaSymptoms.add("eight");
        dyscalculiaSymptoms.add("nine");

        //dyspraxia Symptoms
        dyspraxiaSymptoms.add("ten");
        dyspraxiaSymptoms.add("eleven");
        dyspraxiaSymptoms.add("twelve");

        //adhd sypmtoms
        adhdSymptoms.add("thirteen");
        adhdSymptoms.add("fourteen");
        adhdSymptoms.add("fifteen");

        learnList.add(new ModelLearningDisability("Dyslexia", "dyslexia short description", dyslexiaSymptoms));
        learnList.add(new ModelLearningDisability("Dysgraphia", "dysgraphia short description", dysgraphiaSymptoms));
        learnList.add(new ModelLearningDisability("Dyscalculia", "dyscalculia short description", dyscalculiaSymptoms));
        learnList.add(new ModelLearningDisability("Dyspraxia", "dyspraxia short description", dyspraxiaSymptoms));
        learnList.add(new ModelLearningDisability("ADHD", "adhd short description", adhdSymptoms));


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LearningDisabilityListAdapter(learnList, this);
        recyclerView.setAdapter(adapter);

    }

}