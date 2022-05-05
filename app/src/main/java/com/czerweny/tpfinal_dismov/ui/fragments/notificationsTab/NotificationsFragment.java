package com.czerweny.tpfinal_dismov.ui.fragments.notificationsTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.repositories.NotificationsRepository;
import com.czerweny.tpfinal_dismov.ui.activities.QRScannerActivity;
import com.czerweny.tpfinal_dismov.ui.adapters.NotificationListAdapter;

import java.util.Collections;
import java.util.function.Consumer;

public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NotificationListAdapter notificationsListAdapter;
    private LinearLayout noNotificationsLayout;

    private ImageButton btnRefresh;

    public NotificationsFragment() { }

    public static NotificationsFragment newInstance() {
        NotificationsFragment fragment = new NotificationsFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        btnRefresh = view.findViewById(R.id.btn_notifications_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateView();
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_notificacions_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noNotificationsLayout = (LinearLayout) view.findViewById(R.id.ll_noNotifications);

        updateView();
        return view;
    }

    private void updateView() {
        NotificationsRepository.getNotifications().observe(getViewLifecycleOwner(), notifications -> {
            Collections.reverse(notifications);
            notificationsListAdapter = new NotificationListAdapter(notifications, deleteNotification);
            recyclerView.setAdapter(notificationsListAdapter);

            boolean empty = notificationsListAdapter.getItemCount() == 0;
            recyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);
            noNotificationsLayout.setVisibility(empty ? View.VISIBLE : View.GONE);
        });
    }

    private Consumer<String> deleteNotification = notificationId -> {
        NotificationsRepository.deleteNotification(notificationId);
        Toast.makeText(getContext(), "Notificación eliminada con éxito.", Toast.LENGTH_SHORT).show();
        updateView();
    };

}