package com.czerweny.tpfinal_dismov.backend.models.noticiaUNL;

public class Mediafile{
    private boolean is_principal;
    private String footnote;
    private String attachment;
    private String type;
    private String active;
    private String id;
    private String news_id;
    private String path;

    public Mediafile(boolean is_principal, String footnote, String attachment, String type, String active, String id, String news_id, String path) {
        this.is_principal = is_principal;
        this.footnote = footnote;
        this.attachment = attachment;
        this.type = type;
        this.active = active;
        this.id = id;
        this.news_id = news_id;
        this.path = path;
    }

    public Mediafile() {
    }

    public boolean isIs_principal() {
        return is_principal;
    }
    public void setIs_principal(boolean is_principal) {
        this.is_principal = is_principal;
    }

    public String getFootnote() {
        return footnote;
    }
    public void setFootnote(String footnote) {
        this.footnote = footnote;
    }

    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public String getNews_id() { return news_id; }
    public void setNews_id(String news_id) { this.news_id = news_id; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}
