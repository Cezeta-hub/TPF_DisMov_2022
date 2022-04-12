package com.czerweny.tpfinal_dismov.backend.retrofit;

import com.czerweny.tpfinal_dismov.backend.models.BedeliaResponse;
import com.czerweny.tpfinal_dismov.backend.models.NoticiasUNLResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoticiasUNLWebService {

    @GET("getnewshome/limit:10")
    Call<List<NoticiasUNLResponse>> getNews();
}
