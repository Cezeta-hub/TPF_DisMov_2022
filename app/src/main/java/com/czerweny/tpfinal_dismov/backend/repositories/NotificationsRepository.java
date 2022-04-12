package com.czerweny.tpfinal_dismov.backend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.czerweny.tpfinal_dismov.backend.databases.LocalDB;
import com.czerweny.tpfinal_dismov.backend.databases.room.LocationRoom;
import com.czerweny.tpfinal_dismov.backend.databases.room.NotificationRoom;
import com.czerweny.tpfinal_dismov.backend.models.Location;
import com.czerweny.tpfinal_dismov.backend.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationsRepository {

    public static LiveData<Notification> getNotifications(String notificationId) {
        MediatorLiveData<Notification> data = new MediatorLiveData<>();

        data.addSource(LocalDB.getNotification(notificationId), notificationRoom -> {
            if (notificationRoom != null)
                data.setValue(new Notification(notificationRoom));
        });

        return data;
    }

    public static LiveData<List<Notification>> getNotifications() {
        MediatorLiveData<List<Notification>> data = new MediatorLiveData<>();

        data.addSource(LocalDB.getNotifications(), notificationsRoom -> {
            if (notificationsRoom != null) {
                List<Notification> notifications = new ArrayList<>();

                for (NotificationRoom notificationRoom : notificationsRoom) {
                    notifications.add(new Notification(notificationRoom));
                }

                data.setValue(notifications);
            }
        });

        return data;
    }

    public static void saveNotication(Notification notification) {
        LocalDB.saveNotification(notification);
    }

    public static void deleteNotification(String notificationId) {
        LocalDB.deleteNotification(notificationId);
    }
}
