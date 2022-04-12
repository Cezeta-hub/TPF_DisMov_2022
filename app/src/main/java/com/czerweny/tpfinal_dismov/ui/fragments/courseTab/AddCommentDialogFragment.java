package com.czerweny.tpfinal_dismov.ui.fragments.courseTab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.czerweny.tpfinal_dismov.R;

public class AddCommentDialogFragment extends DialogFragment {

    public static final String COURSE_ID = "COURSE_ID";

    private String courseId;

    public interface AddCommentDialogListener {
        public void onDialogPositiveClick(String body);
    }
    AddCommentDialogFragment.AddCommentDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddCommentDialogFragment.AddCommentDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddCommentDialogListener");
        }
    }

    public AddCommentDialogFragment() { }

    public static AddCommentDialogFragment newInstance() {
        AddCommentDialogFragment fragment = new AddCommentDialogFragment();
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

        View view = inflater.inflate(R.layout.fragment_add_comment_dialog, null);

        TextView tvMateria = (TextView) view.findViewById(R.id.tv_dialog_addComment_course);
        tvMateria.setText("Agregue un comentario a la materia: " + courseId);

        EditText etBody = (EditText) view.findViewById(R.id.et_dialog_addComment_body);

        builder.setView(view);

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String body = etBody.getText().toString();
                listener.onDialogPositiveClick(body);
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