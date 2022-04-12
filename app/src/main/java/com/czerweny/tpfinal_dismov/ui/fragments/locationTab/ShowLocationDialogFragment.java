package com.czerweny.tpfinal_dismov.ui.fragments.locationTab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Location;
import com.czerweny.tpfinal_dismov.backend.repositories.LocationsRepository;

public class ShowLocationDialogFragment extends DialogFragment {

    public static final String LOCATION_ID = "LOCATION_ID";

    private String locationId;
    private Location location;

    public ShowLocationDialogFragment() { }

    public static ShowLocationDialogFragment newInstance() {
        ShowLocationDialogFragment fragment = new ShowLocationDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            locationId = getArguments().getString("LOCATION_ID");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_show_location_dialog, null);

        TextView tv_locationName = (TextView) view.findViewById(R.id.tv_dialogLocation_name);
        TextView tv_locationFloor = (TextView) view.findViewById(R.id.tv_dialogLocation_floor);
        ImageView iv_locationBlueprint = (ImageView) view.findViewById(R.id.iv_dialogLocation_blueprint);

        LocationsRepository.getLocation(locationId).observe(this, location -> {
            this.location = location;

            tv_locationName.setText(location.getName());
            tv_locationFloor.setText(location.getFloor());

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), location.getBlueprint());
            Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(mutableBitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(50);
            float scale = getResources().getDisplayMetrics().density;
            canvas.drawCircle(location.getCoordX()*scale, location.getCoordY()*scale, 40*scale, paint);
            iv_locationBlueprint.setImageBitmap(mutableBitmap);
        });

        builder.setView(view);

        builder.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}