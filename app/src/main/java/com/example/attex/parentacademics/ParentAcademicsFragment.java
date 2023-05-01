package com.example.attex.parentacademics;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.attex.R;

public class ParentAcademicsFragment extends Fragment {

    ImageView maths, science, english, irish, history, geography, standardTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_academics, container, false);

        Intent i = getActivity().getIntent();
        String studentID = i.getStringExtra("studentID");
        String classID = i.getStringExtra("classID");
        String classGrade = i.getStringExtra("classGrade");
        String schoolID = i.getStringExtra("schoolID");

        maths = view.findViewById(R.id.maths);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Maths";

                Intent intent = new Intent(getActivity(), ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        science = view.findViewById(R.id.science);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Science";

                Intent intent = new Intent(getActivity(), ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });


        english = view.findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "English";

                Intent intent = new Intent(getActivity(), ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });

        geography = view.findViewById(R.id.geography);
        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Geography";

                Intent intent = new Intent(getActivity(), ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });

        irish = view.findViewById(R.id.irish);
        irish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Irish";

                Intent intent = new Intent(getActivity(), ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });

        history = view.findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "History";

                Intent intent = new Intent(getActivity(), ParentViewTopicsActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                intent.putExtra("subject", subject);
                startActivity(intent);
            }
        });

        standardTest = view.findViewById(R.id.standardTest);
        standardTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseSubjectActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });



        return view;
    }
}