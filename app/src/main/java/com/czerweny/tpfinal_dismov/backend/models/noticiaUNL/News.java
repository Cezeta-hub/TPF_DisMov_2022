package com.czerweny.tpfinal_dismov.backend.models.noticiaUNL;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class News{
    private String id;
    private String title;
    private String title_for_module;
    private String slug;
    private String is_brief;
    private String volanta;
    private String copete;
    private boolean published;
    private String modified;
    private String body;
    @SerializedName("ContentsNews")
    private ContentsNews contentsNews;
    @SerializedName("Mediafile")
    private ArrayList<Mediafile> mediafile;

    public News(String id, String title, String title_for_module, String slug, String is_brief, String volanta, String copete, boolean published, String modified, String body, ContentsNews contentsNews, ArrayList<Mediafile> mediafile) {
        this.id = id;
        this.title = title;
        this.title_for_module = title_for_module;
        this.slug = slug;
        this.is_brief = is_brief;
        this.volanta = volanta;
        this.copete = copete;
        this.published = published;
        this.modified = modified;
        this.body = body;
        this.contentsNews = contentsNews;
        this.mediafile = mediafile;
    }

    public News() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_for_module() {
        return title_for_module;
    }
    public void setTitle_for_module(String title_for_module) { this.title_for_module = title_for_module; }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIs_brief() {
        return is_brief;
    }
    public void setIs_brief(String is_brief) {
        this.is_brief = is_brief;
    }

    public String getVolanta() {
        return volanta;
    }
    public void setVolanta(String volanta) {
        this.volanta = volanta;
    }

    public String getCopete() {
        return copete;
    }
    public void setCopete(String copete) {
        this.copete = copete;
    }

    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getModified() {
        return modified;
    }
    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public ContentsNews getContentsNews() {
        return contentsNews;
    }
    public void setContentsNews(ContentsNews contentsNews) {
        this.contentsNews = contentsNews;
    }

    public ArrayList<Mediafile> getMediafile() {
        return mediafile;
    }
    public void setMediafile(ArrayList<Mediafile> mediafile) {
        this.mediafile = mediafile;
    }
}
