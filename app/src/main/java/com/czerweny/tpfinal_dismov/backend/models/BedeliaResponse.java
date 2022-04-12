package com.czerweny.tpfinal_dismov.backend.models;

import java.util.ArrayList;

public class BedeliaResponse {

    private int cantidad;
    private ArrayList<Class> clases;

    public BedeliaResponse() {
    }

    public BedeliaResponse(int cantidad, ArrayList<Class> clases) {
        this.cantidad = cantidad;
        this.clases = clases;
    }

    public int getAmount() {
        return cantidad;
    }

    public void setAmount(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<Class> getClasses() {
        return clases;
    }

    public void setClasses(ArrayList<Class> clases) {
        this.clases = clases;
    }
}
