package com.czerweny.tpfinal_dismov.backend.models;

import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseFile;

public class File {
    private String courseId;
    private String title;
    private String author;
    private String description;
    private String link;

    public File(String courseId, String title, String author, String description, String link) {
        this.courseId = courseId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.link = link;
    }

    public File(String courseId, FirebaseFile firebaseFile) {
        this.courseId = courseId;
        this.title = firebaseFile.getTitle();
        this.author = firebaseFile.getAuthor();
        this.description = firebaseFile.getDescription();
        this.link = firebaseFile.getLink();
    }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) { this.author = author; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}
