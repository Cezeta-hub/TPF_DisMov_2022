package com.czerweny.tpfinal_dismov.ui.fragments.locationTab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Article;
import com.czerweny.tpfinal_dismov.backend.models.NoticiasUNLResponse;
import com.czerweny.tpfinal_dismov.backend.repositories.LocationsRepository;
import com.czerweny.tpfinal_dismov.backend.retrofit.NoticiasUNLWebService;
import com.czerweny.tpfinal_dismov.backend.retrofit.RetrofitClientUNL;
import com.czerweny.tpfinal_dismov.ui.adapters.LocationListAdapter;
import com.czerweny.tpfinal_dismov.ui.adapters.NewsListAdapter;
import com.czerweny.tpfinal_dismov.ui.fragments.newsTab.ArticleDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationFragment extends Fragment {

    private RecyclerView recyclerView;

    public LocationFragment() { }

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

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

}