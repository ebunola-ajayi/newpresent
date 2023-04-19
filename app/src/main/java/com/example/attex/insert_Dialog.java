package com.example.attex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class insert_Dialog extends AppCompatDialogFragment {
    EditText topicET;
    EditText noteET;
    EditText gradeET;


    insert_DialogInterface insert_dialogInterface;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.popup_add_topic, null);

        builder.setView(view).setTitle("New Topic").setNegativeButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String topic = topicET.getText().toString();
                String note = noteET.getText().toString();
                String grade = gradeET.getText().toString();
               // int position = Integer.parseInt(editPos.getText().toString());
                insert_dialogInterface.applyTexts(topic, note, grade);
            }
        })
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        topicET = view.findViewById(R.id.topicET);
        noteET = view.findViewById(R.id.noteET);
        gradeET = view.findViewById(R.id.gradeET);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        insert_dialogInterface = (insert_DialogInterface) context;
        super.onAttach(context);
    }

    public interface insert_DialogInterface {
        //public void applyTexts(String lineOne, String lineTwo, int position);
        public void applyTexts(String lineOne, String lineTwo, String lineThree);
    }



}
