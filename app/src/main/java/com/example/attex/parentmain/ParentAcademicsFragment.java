package com.example.attex.parentmain;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.attex.R;

public class ParentAcademicsFragment extends Fragment {

    ImageView maths;
    ImageView science;
    ImageView english;
    ImageView irish;
    ImageView history;
    ImageView geography;
    ImageView standardTest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_academics, container, false);

        Intent i = getActivity().getIntent();
        String studentID = i.getStringExtra("studentID");
        System.out.println(studentID);

        Intent i2 = getActivity().getIntent();
        String classID = i2.getStringExtra("classID");
        System.out.println(classID);

        Intent i3 = getActivity().getIntent();
        String classGrade = i3.getStringExtra("classGrade2");
        System.out.println(classGrade);

        Intent i4 = getActivity().getIntent();
        String schoolID = i4.getStringExtra("schoolID2");
        System.out.println(schoolID);

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
                Intent intent = new Intent(getActivity(), ParentViewStandardResultActivity.class);
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