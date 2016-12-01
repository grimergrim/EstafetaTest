package com.avtologistika.test.api;

import com.avtologistika.test.entities.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpEndpointsApi {

    @GET("/api/mobilesurveytasks/gettestsurveytasks")
    Call<List<Task>> getTasks();

}

