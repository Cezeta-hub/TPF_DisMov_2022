package com.czerweny.tpfinal_dismov.backend.retrofit;

import com.czerweny.tpfinal_dismov.backend.models.BedeliaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BedeliaWebService {

    @GET("webservice-bedelia-movil.php")
    Call<BedeliaResponse> getClassesByDate(@Query("fecha_inicio") String fecha);
}
