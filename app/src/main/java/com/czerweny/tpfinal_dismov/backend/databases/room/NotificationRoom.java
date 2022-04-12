package com.czerweny.tpfinal_dismov.backend.databases.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.czerweny.tpfinal_dismov.backend.models.Location;
import com.czerweny.tpfinal_dismov.backend.models.Notification;

@Entity(tableName = "notifications")
public class NotificationRoom {

    @NonNull
    @PrimaryKey
    public String id = "";

    public String title;
    public String dateTime;
    public String message;

    public NotificationRoom(Notification notification) {
        this.id = notification.getId();
        this.title = notification.getTitle();
        this.dateTime = notification.getDateTime();
        this.message = notification.getMessage();
    }

    public NotificationRoom() { }
}
