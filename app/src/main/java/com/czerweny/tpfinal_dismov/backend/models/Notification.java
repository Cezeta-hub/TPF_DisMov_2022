package com.czerweny.tpfinal_dismov.backend.models;

import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseFile;
import com.czerweny.tpfinal_dismov.backend.databases.room.NotificationRoom;

public class Notification {
    private String id;
    private String title;
    private String dateTime;
    private String message;

    public Notification(String id, String title, String dateTime, String message) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.message = message;
    }

    public Notification(NotificationRoom notificationRoom) {
        this.id = notificationRoom.id;
        this.title = notificationRoom.title;
        this.dateTime = notificationRoom.dateTime;
        this.message = notificationRoom.message;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) { this.message = message; }
}
