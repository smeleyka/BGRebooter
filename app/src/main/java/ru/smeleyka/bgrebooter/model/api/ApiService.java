package ru.smeleyka.bgrebooter.model.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.smeleyka.bgrebooter.model.entity.LoginResponse;
import ru.smeleyka.bgrebooter.model.entity.ZabbixObject;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api_jsonrpc.php")
    Observable<LoginResponse> makeRequest(@Body String loginRequest);

    @Headers("Content-Type: application/json")
    @POST("api_jsonrpc.php")
    Observable<String> makeTestRequest(@Body String loginRequest);
}