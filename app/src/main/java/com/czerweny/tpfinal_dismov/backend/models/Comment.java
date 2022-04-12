package com.czerweny.tpfinal_dismov.backend.models;

import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseComment;

public class Comment {
    private String courseId;
    private String author;
    private String body;

    public Comment(String courseId, String author, String body) {
        this.courseId = courseId;
        this.author = author;
        this.body = body;
    }

    public Comment(String courseId, FirebaseComment firebaseComment) {
        this.courseId = courseId;
        this.author = firebaseComment.getAuthor();
        this.body = firebaseComment.getBody();
    }

    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
