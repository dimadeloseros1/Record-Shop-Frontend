package com.example.recordshopfrontend.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://recordshopapi-env.eba-awmmxgqc.eu-west-2.elasticbeanstalk.com/api/v1/";

    public static AlbumsApiService getService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit.create(AlbumsApiService.class);
    }
}
