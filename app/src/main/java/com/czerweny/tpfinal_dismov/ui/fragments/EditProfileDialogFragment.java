package com.czerweny.tpfinal_dismov.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.czerweny.tpfinal_dismov.R;

import java.util.Calendar;
import java.util.Date;

public class EditProfileDialogFragment extends DialogFragment {

    public interface EditProfileDialogListener {
        public void onDialogPositiveClick(String birthDate, String bio);
    }

    EditProfileDialogFragment.EditProfileDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (EditProfileDialogFragment.EditProfileDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement EditProfileDialogListener");
        }
    }

    public EditProfileDialogFragment() { }

    public static EditProfileDialogFragment newInstance() {
        EditProfileDialogFragment fragment = new EditProfileDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_edit_profile_dialog, null);

        CalendarView cvCalendar = (CalendarView) view.findViewById(R.id.cv_dialog_editProfile_calendar);
        EditText etBio = (EditText) view.findViewById(R.id.et_dialog_editProfile_bio);

        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);

                calendarView.setDate(calendar.getTimeInMillis());
            }
        });

        builder.setView(view);

        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String birthDate = (String) DateFormat.format("dd/MM/yyyy", new Date(cvCalendar.getDate()));
                String bio = etBio.getText().toString();
                listener.onDialogPositiveClick(birthDate, bio);
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