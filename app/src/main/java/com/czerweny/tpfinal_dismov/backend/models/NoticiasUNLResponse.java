package com.czerweny.tpfinal_dismov.backend.models;

import com.czerweny.tpfinal_dismov.backend.models.noticiaUNL.Content;
import com.czerweny.tpfinal_dismov.backend.models.noticiaUNL.News;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NoticiasUNLResponse {

    @SerializedName("Content")
    private Content content;
    @SerializedName("News")
    private ArrayList<News> news;

    public NoticiasUNLResponse(Content content, ArrayList<News> news) {
        this.content = content;
        this.news = news;
    }

    public NoticiasUNLResponse() {
    }

    public Content getContent() {
        return content;
    }
    public void setContent(Content content) {
        this.content = content;
    }

    public ArrayList<News> getNews() {
        return news;
    }
    public void setNews(ArrayList<News> news) {
        this.news = news;
    }
}
