package com.czerweny.tpfinal_dismov.backend.databases.roomdao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.czerweny.tpfinal_dismov.backend.databases.room.LocationRoom;

import java.util.List;

@Dao
public abstract class LocationRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(LocationRoom location);

    @Query("DELETE FROM locations WHERE id=:locationId")
    public abstract void delete(String locationId);

    @Query("SELECT * FROM locations")
    public abstract LiveData<List<LocationRoom>> load();

    @Query("SELECT * FROM locations WHERE id=:locationId")
    public abstract LiveData<LocationRoom> loadById(String locationId);
}
