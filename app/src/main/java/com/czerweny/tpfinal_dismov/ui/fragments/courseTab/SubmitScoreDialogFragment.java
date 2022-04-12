package com.czerweny.tpfinal_dismov.ui.fragments.courseTab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.czerweny.tpfinal_dismov.R;

public class SubmitScoreDialogFragment extends DialogFragment {

    public static final String COURSE_ID = "COURSE_ID";

    private String courseId;

    public interface SubmitScoreDialogListener {
        public void onDialogPositiveClick(Double score);
    }
    SubmitScoreDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SubmitScoreDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement SubmitScoreDialogListener");
        }
    }

    public SubmitScoreDialogFragment() { }

    public static SubmitScoreDialogFragment newInstance() {
        SubmitScoreDialogFragment fragment = new SubmitScoreDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseId = getArguments().getString("COURSE_ID");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_score_course_dialog, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_dialogSubmitScore_course);
        textView.setText("Califique la materia: " + courseId);

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rb_dialogSubmitScore_score);

        builder.setView(view);

        builder.setPositiveButton("Calificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Double score = (double) ratingBar.getRating();
                listener.onDialogPositiveClick(score);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}