package com.czerweny.tpfinal_dismov.ui.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Location;
import com.czerweny.tpfinal_dismov.backend.models.Notification;

import java.util.List;
import java.util.function.Consumer;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {

    private List<Notification> notifications;
    private Consumer<String> deleteNotification;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_notificationTitle;
        public TextView tv_notificationDateTime;
        public TextView tv_notificationMessage;
        public ImageButton bt_deleteNotification;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_notificationTitle = (TextView) itemView.findViewById(R.id.tv_cardNotification_title);
            tv_notificationDateTime = (TextView) itemView.findViewById(R.id.tv_cardNotification_dateTime);
            tv_notificationMessage = (TextView) itemView.findViewById(R.id.tv_cardNotification_message);
            bt_deleteNotification = (ImageButton) itemView.findViewById(R.id.bt_cardNotification_delete);
        }

        public void setClickListener(Runnable listener) {
            bt_deleteNotification.setOnClickListener(v -> listener.run());
        }
    }

    public NotificationListAdapter(List<Notification> notifications, Consumer<String> deleteNotification) {
        this.notifications = notifications;
        this.deleteNotification = deleteNotification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notification, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        holder.tv_notificationTitle.setText(notification.getTitle());
        holder.tv_notificationDateTime.setText(notification.getDateTime());
        holder.tv_notificationMessage.setText(notification.getMessage());
        holder.setClickListener(() -> deleteNotification.accept(notification.getId()));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

}
