package com.czerweny.tpfinal_dismov.backend.models;

public class Class {

    private String id;
    private String area;
    private String aula;
    private String evento;
    private String descripcion;
    private String fecha_inicio;
    private String hora_inicio;
    private String fecha_fin;
    private String hora_fin;
    private String estado;

    public Class(String id, String area, String aula, String evento, String descripcion, String fecha_inicio, String hora_inicio, String fecha_fin, String hora_fin, String estado) {
        this.id = id;
        this.area = area;
        this.aula = aula;
        this.evento = evento;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.fecha_fin = fecha_fin;
        this.hora_fin = hora_fin;
        this.estado = estado;
    }

    public Class() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getClassroom() {
        return aula;
    }
    public void setClassroom(String classroom) {
        this.aula = classroom;
    }

    public String getEvent() {
        return evento;
    }
    public void setEvent(String event) {
        this.evento = event;
    }

    public String getDescription() {
        return descripcion;
    }
    public void setDescription(String description) {
        this.descripcion = description;
    }

    public String getStartDate() {
        return fecha_inicio;
    }
    public void setStartDate(String startDate) {
        this.fecha_inicio = startDate;
    }

    public String getStartTime() {
        return hora_inicio;
    }
    public void setStartTime(String startTime) {
        this.hora_inicio = startTime;
    }

    public String getEndDate() {
        return fecha_fin;
    }
    public void setEndDate(String endDate) {
        this.fecha_fin = endDate;
    }

    public String getEndTime() {
        return hora_fin;
    }
    public void setEndTime(String endTime) {
        this.hora_fin = endTime;
    }

    public String getStatus() {
        return estado;
    }
    public void setStatus(String status) {
        this.estado = status;
    }
}
