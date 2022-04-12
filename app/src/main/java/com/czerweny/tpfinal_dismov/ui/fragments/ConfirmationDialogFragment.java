package com.czerweny.tpfinal_dismov.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czerweny.tpfinal_dismov.R;

public class ConfirmationDialogFragment extends DialogFragment {

    private String title;
    private String message;
    private String okAction;
    private Runnable onSuccessFunction;

    public ConfirmationDialogFragment(String title, String message, String okAction, Runnable onSuccessFunction) {
        this.title = title;
        this.message = message;
        this.okAction = okAction;
        this.onSuccessFunction = onSuccessFunction;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(okAction, (dialog, which) -> onSuccessFunction.run());
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        return builder.create();
    }
}