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
        dyslexiaSymptoms.add("Reversing the position of letters");
        dyslexiaSymptoms.add("Struggle with reading comprehension");
        dyslexiaSymptoms.add("Delayed speech");

        //dysgraphia Symptoms
        dysgraphiaSymptoms.add("Difficulty with written expression");
        dysgraphiaSymptoms.add("Problems organizing thoughts and ideas in writing");
        dysgraphiaSymptoms.add("Taking a long time to write");

        //dyscalculia Symptoms
        dyscalculiaSymptoms.add("Being unsure of how to do basic math problems");
        dyscalculiaSymptoms.add("Difficulty following the order of operations");
        dyscalculiaSymptoms.add("Finding it challenging to count and group numbers together");

        //dyspraxia Symptoms
        dyspraxiaSymptoms.add("Problems with movement, coordination, and perception");
        dyspraxiaSymptoms.add("Problems with writing, dressing, and organizing");
        dyspraxiaSymptoms.add("Difficulty with new skills");

        //adhd sypmtoms
        adhdSymptoms.add("Inability to sit still");
        adhdSymptoms.add("Being disorganized or forgetful");
        adhdSymptoms.add("Difficulty staying quiet and attentive");

        learnList.add(new ModelLearningDisability("Dyslexia", "Dyslexia is the number one learning disability that affects people of all ages. It affects a person’s reading and language processing skills.", dyslexiaSymptoms));
        learnList.add(new ModelLearningDisability("Dysgraphia", "Dysgraphia is a learning disability that affects a person’s ability to write.", dysgraphiaSymptoms));
        learnList.add(new ModelLearningDisability("Dyscalculia", "Dyscalculia is a learning disability that affects math skills.", dyscalculiaSymptoms));
        learnList.add(new ModelLearningDisability("Dyspraxia", "Dyspraxia is a neurological disorder that affects a person’s ability to plan and coordinate movement.", dyspraxiaSymptoms));
        learnList.add(new ModelLearningDisability("ADHD", "ADHD, or Attention Deficit Hyperactivity Disorder, is a neurodevelopmental disorder that affects a person’s ability to pay attention and control impulsivity.", adhdSymptoms));


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LearningDisabilityListAdapter(learnList, this);
        recyclerView.setAdapter(adapter);

    }

}