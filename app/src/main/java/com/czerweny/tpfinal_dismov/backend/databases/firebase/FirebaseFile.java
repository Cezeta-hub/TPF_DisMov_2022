package com.czerweny.tpfinal_dismov.backend.databases.firebase;

import com.czerweny.tpfinal_dismov.backend.models.File;

import java.util.HashMap;

public class FirebaseFile {
    private String title;
    private String author;
    private String description;
    private String link;

    public FirebaseFile(String title, String author, String description, String link) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.link = link;
    }
    public FirebaseFile(File file) {
        this.title = file.getTitle();
        this.author = file.getAuthor();
        this.description = file.getDescription();
        this.link = file.getLink();
    }

    public FirebaseFile() { }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

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

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("title", title);
        result.put("author", author);
        result.put("description", description);
        result.put("link", link);

        return result;
    }
}
