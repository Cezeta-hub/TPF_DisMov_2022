package com.czerweny.tpfinal_dismov.backend.databases.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.czerweny.tpfinal_dismov.backend.models.Location;

@Entity(tableName = "locations")
public class LocationRoom {

    @NonNull
    @PrimaryKey
    public String id = "";

    public String name;
    public String floor;
    public Integer blueprint;
    public Integer coordX;
    public Integer coordY;

    public LocationRoom(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.floor = location.getFloor();
        this.blueprint = location.getBlueprint();
        this.coordX = location.getCoordX();
        this.coordY = location.getCoordY();
    }

    public LocationRoom() { }
}
