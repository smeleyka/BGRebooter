package ru.smeleyka.bgrebooter.model.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api_jsonrpc.php")
    Observable<String> makeRequest(@Body String loginRequest);

}