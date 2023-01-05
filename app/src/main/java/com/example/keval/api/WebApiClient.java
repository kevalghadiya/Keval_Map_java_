package com.example.keval.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebApiClient {
    private static final String BASE_URL = "https://api.sygictraveldata.com/v2.5/en/";

    private static Retrofit retrofit = null;

    public static synchronized WebServices getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(WebServices.class);
    }
}
