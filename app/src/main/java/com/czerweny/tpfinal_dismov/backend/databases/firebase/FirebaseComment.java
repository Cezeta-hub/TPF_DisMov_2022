package com.czerweny.tpfinal_dismov.backend.databases.firebase;

import com.czerweny.tpfinal_dismov.backend.models.Comment;

import java.util.HashMap;

public class FirebaseComment {
    private String author;
    private String body;

    public FirebaseComment(String author, String body) {
        this.author = author;
        this.body = body;
    }

    public FirebaseComment(Comment comment) {
        this.author = comment.getAuthor();
        this.body = comment.getBody();
    }

    public FirebaseComment() { }

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

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("author", author);
        result.put("body", body);

        return result;
    }
}
