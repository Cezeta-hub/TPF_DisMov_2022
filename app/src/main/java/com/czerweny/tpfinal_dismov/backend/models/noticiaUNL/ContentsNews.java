package com.czerweny.tpfinal_dismov.backend.models.noticiaUNL;

public class ContentsNews{
    private String id;
    private String content_id;
    private String news_id;

    public ContentsNews(String id, String content_id, String news_id) {
        this.id = id;
        this.content_id = content_id;
        this.news_id = news_id;
    }

    public ContentsNews() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }
}
