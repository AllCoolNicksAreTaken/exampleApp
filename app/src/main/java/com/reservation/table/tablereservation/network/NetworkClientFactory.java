package com.reservation.table.tablereservation.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created on 14/07/17.
 */

public class NetworkClientFactory {

    final static String BASE_URL = "https://s3-eu-west-1.amazonaws.com/quandoo-assessment/";

    public static Retrofit createDefaultClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(createOkHTTPClient())
                .build();
    }

    private static OkHttpClient createOkHTTPClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //add interceptor for logging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        builder.addInterceptor(loggingInterceptor);

        return builder.build();
    }




}
