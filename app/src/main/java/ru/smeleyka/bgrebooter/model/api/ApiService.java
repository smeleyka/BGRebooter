package ru.smeleyka.bgrebooter.model.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type: application/json-rpc")
    //@POST("post")
    @POST("api_jsonrpc.php")
    Observable<String> getLoginAnswer(@Body String loginRequest);
}
//    @GET("users/{user}/repos")
//    Observable<List<User.Repos>> getUserRepos(@Path("user") String userName);