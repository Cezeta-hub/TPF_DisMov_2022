package com.czerweny.tpfinal_dismov.backend.models;

import com.czerweny.tpfinal_dismov.backend.databases.room.LocationRoom;

public class Location {

    private String id;
    private String name;
    private String floor;
    private Integer blueprint;
    private Integer coordX;
    private Integer coordY;

    public Location(String id, String name, String floor, Integer blueprint, Integer coordX, Integer coordY) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.blueprint = blueprint;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Location(LocationRoom locationRoom) {
        this.id = locationRoom.id;
        this.name = locationRoom.name;
        this.floor = locationRoom.floor;
        this.blueprint = locationRoom.blueprint;
        this.coordX = locationRoom.coordX;
        this.coordY = locationRoom.coordY;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getBlueprint() {
        return blueprint;
    }
    public void setBlueprint(Integer blueprint) {
        this.blueprint = blueprint;
    }

    public Integer getCoordX() {
        return coordX;
    }
    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }
    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }
}
