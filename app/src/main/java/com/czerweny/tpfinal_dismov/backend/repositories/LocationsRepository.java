package com.czerweny.tpfinal_dismov.backend.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.czerweny.tpfinal_dismov.backend.databases.LocalDB;
import com.czerweny.tpfinal_dismov.backend.databases.RemoteDB;
import com.czerweny.tpfinal_dismov.backend.databases.room.LocationRoom;
import com.czerweny.tpfinal_dismov.backend.models.Comment;
import com.czerweny.tpfinal_dismov.backend.models.Course;
import com.czerweny.tpfinal_dismov.backend.models.File;
import com.czerweny.tpfinal_dismov.backend.models.Location;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LocationsRepository {

    public static LiveData<Location> getLocation(String locationId) {
        MediatorLiveData<Location> data = new MediatorLiveData<>();

        data.addSource(LocalDB.getLocation(locationId), locationRoom -> {
            if (locationRoom != null)
                data.setValue(new Location(locationRoom));
        });

        return data;
    }

    public static LiveData<List<Location>> getLocations() {
        MediatorLiveData<List<Location>> data = new MediatorLiveData<>();

        data.addSource(LocalDB.getLocations(), locationsRoom -> {
            if (locationsRoom != null) {
                List<Location> locations = new ArrayList<>();

                for (LocationRoom locationRoom : locationsRoom) {
                    locations.add(new Location(locationRoom));
                }

                data.setValue(locations);
            }
        });

        return data;
    }

    public static void saveLocation(Location location) {
        LocalDB.saveLocation(location);
    }
}
