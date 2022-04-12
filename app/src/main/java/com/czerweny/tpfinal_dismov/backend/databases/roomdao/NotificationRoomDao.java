package com.czerweny.tpfinal_dismov.backend.databases.roomdao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.czerweny.tpfinal_dismov.backend.databases.room.LocationRoom;
import com.czerweny.tpfinal_dismov.backend.databases.room.NotificationRoom;

import java.util.List;

@Dao
public abstract class NotificationRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(NotificationRoom notification);

    @Query("DELETE FROM notifications WHERE id=:notificationId")
    public abstract void delete(String notificationId);

    @Query("SELECT * FROM notifications")
    public abstract LiveData<List<NotificationRoom>> load();

    @Query("SELECT * FROM notifications WHERE id=:notificationId")
    public abstract LiveData<NotificationRoom> loadById(String notificationId);
}
