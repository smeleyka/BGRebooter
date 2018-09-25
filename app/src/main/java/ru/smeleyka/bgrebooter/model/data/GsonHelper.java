package ru.smeleyka.bgrebooter.model.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GsonHelper {

    private Gson gson;

    @Inject
    public GsonHelper() {
        this.gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        T obj = gson.fromJson(json, classOfT);
        return obj;
    }

    public String toJson(Object src) {
        return gson.toJson(src, src.getClass());
    }
}
