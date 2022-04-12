package com.czerweny.tpfinal_dismov.ui.fragments.calendarTab;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.BedeliaResponse;
import com.czerweny.tpfinal_dismov.backend.retrofit.BedeliaWebService;
import com.czerweny.tpfinal_dismov.backend.retrofit.RetrofitClientFich;
import com.czerweny.tpfinal_dismov.ui.adapters.ClassesListAdapter;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CalendarFragment extends Fragment {

    private TextView tvDate;
    private TextView tvClasses;
    private Button btnSelectDate;
    private RecyclerView recyclerView;
    private LinearLayout noClassesLayout;
    private BedeliaWebService service;

    public CalendarFragment() {
        Retrofit retrofit = RetrofitClientFich.getInstance();
        service = retrofit.create(BedeliaWebService.class);
    }

    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }

        getParentFragmentManager().setFragmentResultListener("fecha", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                long fechaInMillis = bundle.getLong("fecha");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(fechaInMillis);
                actualizarVista(calendar.getTime());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        tvDate = (TextView) view.findViewById(R.id.tv_calendar_date);
        tvClasses = (TextView) view.findViewById(R.id.tv_calendar_classes);
        btnSelectDate = (Button) view.findViewById(R.id.bt_calendar_pickDate);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_calendar_classes);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new DatePickerDialogFragment();
                dialog.show(getParentFragmentManager(), "DatePickerDialogFragment");
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        noClassesLayout = (LinearLayout) view.findViewById(R.id.ll_calendar_noClasses);

        actualizarVista(Calendar.getInstance().getTime());

        return view;
    }

    private void actualizarVista(Date date) {
        getInfoBedelia(date);
    }

    private void getInfoBedelia(Date date) {
        Call<BedeliaResponse> bedeliaResponseCall = service.getClassesByDate((String) DateFormat.format("yyyy-MM-dd", date));

        bedeliaResponseCall.enqueue(new Callback<BedeliaResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<BedeliaResponse> call, Response<BedeliaResponse> response) {
                if (response.isSuccessful()) {
                    BedeliaResponse bedeliaResponse = response.body();

                    String dayOfTheWeek = (String) DateFormat.format("EEEE", date);
                    String day = (String) DateFormat.format("dd", date);
                    String month = (String) DateFormat.format("MMM", date);
                    String year = (String) DateFormat.format("yyyy", date);

                    tvDate.setText(dayOfTheWeek + ", " + day + " de " + month + " de " + year);
                    tvClasses.setText("Clases (" + bedeliaResponse.getAmount() + ")");
                    ClassesListAdapter classesListAdapter = new ClassesListAdapter(bedeliaResponse.getClasses());
                    recyclerView.setAdapter(classesListAdapter);

                    boolean empty = classesListAdapter.getItemCount() == 0;
                    recyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);
                    noClassesLayout.setVisibility(empty ? View.VISIBLE : View.GONE);
                } else {
                    Log.e("BEDELIA", " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<BedeliaResponse> call, Throwable t) {
                Log.e("BEDELIA", " onFailure: " + t.getMessage());
            }
        });
    }
}