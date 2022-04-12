package com.czerweny.tpfinal_dismov.backend.databases;

import androidx.lifecycle.LiveData;

import com.czerweny.tpfinal_dismov.MyApplication;
import com.czerweny.tpfinal_dismov.backend.databases.room.LocationRoom;
import com.czerweny.tpfinal_dismov.backend.databases.room.NotificationRoom;
import com.czerweny.tpfinal_dismov.backend.databases.roomdao.LocationRoomDao;
import com.czerweny.tpfinal_dismov.backend.databases.roomdao.NotificationRoomDao;
import com.czerweny.tpfinal_dismov.backend.models.Location;
import com.czerweny.tpfinal_dismov.backend.models.Notification;

import java.util.List;

public class LocalDB {

    static private final AppDatabase database = MyApplication.getDatabase();
    static private final NotificationRoomDao notificationRoomDao = database.notificationRoomDao();
    static private final LocationRoomDao locationRoomDao = database.locationRoomDao();

    public static void saveNotification(Notification notification) {
        notificationRoomDao.save(new NotificationRoom(notification));
    }

    public static LiveData<NotificationRoom> getNotification(String notificationId) {
        return notificationRoomDao.loadById(notificationId);
    }

    public static LiveData<List<NotificationRoom>> getNotifications() {
        return notificationRoomDao.load();
    }

    public static void deleteNotification(String notificationId) {
        notificationRoomDao.delete(notificationId);
    }

    public static void saveLocation(Location location) {
        locationRoomDao.save(new LocationRoom(location));
    }

    public static LiveData<LocationRoom> getLocation(String locationId) {
        return locationRoomDao.loadById(locationId);
    }

    public static LiveData<List<LocationRoom>> getLocations() {
        return locationRoomDao.load();
    }

    public static void deleteLocation(String locationId) {
        locationRoomDao.delete(locationId);
    }
}
