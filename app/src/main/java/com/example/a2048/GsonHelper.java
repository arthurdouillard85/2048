package com.example.a2048;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class GsonHelper {
    private static final Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> List<T> fromJsonArray(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
