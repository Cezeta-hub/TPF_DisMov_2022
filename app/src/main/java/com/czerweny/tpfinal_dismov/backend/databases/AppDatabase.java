package com.czerweny.tpfinal_dismov.backend.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.czerweny.tpfinal_dismov.backend.databases.room.LocationRoom;
import com.czerweny.tpfinal_dismov.backend.databases.room.NotificationRoom;
import com.czerweny.tpfinal_dismov.backend.databases.roomdao.LocationRoomDao;
import com.czerweny.tpfinal_dismov.backend.databases.roomdao.NotificationRoomDao;

@Database(
        entities = { NotificationRoom.class, LocationRoom.class },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NotificationRoomDao notificationRoomDao();
    public abstract LocationRoomDao locationRoomDao();
}
