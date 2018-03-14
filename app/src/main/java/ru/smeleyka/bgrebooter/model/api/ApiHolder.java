package ru.smeleyka.bgrebooter.model.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiHolder
{
    private static ApiHolder instance = new ApiHolder();

    public static synchronized ApiHolder getInstance()
    {
        if (instance == null)
        {
            instance = new ApiHolder();
        }
        return instance;
    }

    private ApiService api;

    private ApiHolder()
    {
        api = new Retrofit.Builder()
                .baseUrl("https://isp.vbg.ru/zabbix/")
                //.baseUrl("http://192.168.5.101:8000/")
                //.baseUrl("http://httpbin.org/")
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public ApiService getApi()
    {
        return api;
    }
}
