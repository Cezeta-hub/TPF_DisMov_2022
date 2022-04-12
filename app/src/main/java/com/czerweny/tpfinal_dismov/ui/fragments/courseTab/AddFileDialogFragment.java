package com.czerweny.tpfinal_dismov.ui.fragments.courseTab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.czerweny.tpfinal_dismov.R;

public class AddFileDialogFragment extends DialogFragment {

    public static final String COURSE_ID = "COURSE_ID";

    private String courseId;

    public interface AddFileDialogListener {
        public void onDialogPositiveClick(String titulo, String descripcion, String link);
    }
    AddFileDialogFragment.AddFileDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddFileDialogFragment.AddFileDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddFileDialogListener");
        }
    }

    public AddFileDialogFragment() { }

    public static AddFileDialogFragment newInstance() {
        AddFileDialogFragment fragment = new AddFileDialogFragment();
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

        View view = inflater.inflate(R.layout.fragment_add_file_dialog, null);

        TextView tvCourse = (TextView) view.findViewById(R.id.tv_dialog_addFile_course);
        tvCourse.setText("Agregue un archivo a la materia: " + courseId);

        EditText etTitulo = (EditText) view.findViewById(R.id.et_dialog_addFile_fileTitle);
        EditText etDescripcion = (EditText) view.findViewById(R.id.et_dialog_addFile_description);
        EditText etLink = (EditText) view.findViewById(R.id.et_dialog_addFile_link);

        builder.setView(view);

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                listener.onDialogPositiveClick(
                        etTitulo.getText().toString(),
                        etDescripcion.getText().toString(),
                        etLink.getText().toString());
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