package com.czerweny.tpfinal_dismov.backend.models;

public class Article {
    private String id;
    private String dateTime;
    private String section; // volanta
    private String headline;
    private String drophead; // copete
    private String imagePath;

    public Article(String id, String headline, String dateTime, String section, String drophead, String imagePath) {
        this.id = id;
        this.headline = headline;
        this.dateTime = dateTime;
        this.section = section;
        this.drophead = drophead;
        this.imagePath = imagePath;
    }

    public Article() { }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String fechaHora) {
        this.dateTime = dateTime;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }

    public String getDrophead() {
        return drophead;
    }
    public void setDrophead(String drophead) {
        this.drophead = drophead;
    }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
