package com.zybooks.studyhelpergame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ClassInfoDialog extends AppCompatDialogFragment {

    private EditText testName;
    private EditText questionAnswer;
    private ClassInfoDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view).setTitle("Question")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newQuestion = testName.getText().toString();
                String newQuestionAnswer = questionAnswer.getText().toString();
                listener.applyTexts(newQuestion, newQuestionAnswer);

            }
        });

        testName = view.findViewById(R.id.Question);
        questionAnswer = view.findViewById(R.id.answer);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ClassInfoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must Implement ExampleDialogListener");
        }
    }


    public interface ClassInfoDialogListener{
        void applyTexts(String Question, String theAnswer);
    }
}