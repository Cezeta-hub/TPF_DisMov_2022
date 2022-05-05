package com.czerweny.tpfinal_dismov.ui.fragments.locationTab;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Article;
import com.czerweny.tpfinal_dismov.backend.models.NoticiasUNLResponse;
import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.LocationsRepository;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;
import com.czerweny.tpfinal_dismov.backend.retrofit.NoticiasUNLWebService;
import com.czerweny.tpfinal_dismov.backend.retrofit.RetrofitClientUNL;
import com.czerweny.tpfinal_dismov.ui.activities.CourseDetailsActivity;
import com.czerweny.tpfinal_dismov.ui.activities.QRScannerActivity;
import com.czerweny.tpfinal_dismov.ui.adapters.LocationListAdapter;
import com.czerweny.tpfinal_dismov.ui.adapters.NewsListAdapter;
import com.czerweny.tpfinal_dismov.ui.fragments.calendarTab.DatePickerDialogFragment;
import com.czerweny.tpfinal_dismov.ui.fragments.newsTab.ArticleDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationFragment extends Fragment {

    static public final int QR_ACTIVITY = 42;
    private RecyclerView recyclerView;

    private ImageButton btnQRreader;
    String qrLink = null;

    public LocationFragment() { }

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.qrLink = getArguments().getString("qrLink");
            if (this.qrLink != null) {
                locationDisplay.accept(this.qrLink);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        btnQRreader = view.findViewById(R.id.btn_location_QR);
        btnQRreader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent QRScannerIntent = new Intent(requireActivity(), QRScannerActivity.class);
                startActivityForResult(QRScannerIntent, QR_ACTIVITY);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_location_locations);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LocationsRepository.getLocations().observe(getViewLifecycleOwner(),
                locations -> recyclerView.setAdapter(new LocationListAdapter(locations, locationDisplay)));

        return view;
    }

    private Consumer<String> locationDisplay = locationId -> {
        Bundle args = new Bundle();
        args.putString(ShowLocationDialogFragment.LOCATION_ID, locationId);

        DialogFragment dialog = new ShowLocationDialogFragment();
        dialog.setArguments(args);
        dialog.show(getParentFragmentManager(), "ShowLocationDialogFragment");
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_ACTIVITY) {
            if (resultCode == RESULT_OK){
                Uri qrLink = Uri.parse(data.getStringExtra("SCAN_RESULT"));
                this.qrLink = qrLink.getQueryParameter("classroom");
                if (this.qrLink != null) {
                    locationDisplay.accept(this.qrLink);
                }
            }
        }
    }
}