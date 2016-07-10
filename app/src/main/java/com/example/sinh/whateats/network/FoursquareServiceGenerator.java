package com.example.sinh.whateats.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoursquareServiceGenerator {

    public static final String API_BASE_URL = "https://api.foursquare.com/v2/";
    private static HttpLoggingInterceptor logging;
    private static OkHttpClient.Builder httpClientBuilder;
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        httpClientBuilder = new OkHttpClient.Builder();
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(logging);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl.Builder builder = chain.request().url().newBuilder();
                builder.addQueryParameter("client_id", "AVAYIEK1UEMN1JDKTBZCYKRYWOMVIORT3IWSOXVP4HFESYKD");
                builder.addQueryParameter("client_secret", "HXGMTLOS4KKDI1EJS1BMJ0O3XKGULIX22WUJJOSF3MYT2AHM");
                builder.addQueryParameter("v", "20130815");
                HttpUrl url = builder.build();
                Request.Builder reqBuilder = chain.request().newBuilder();
                reqBuilder.url(url);
                return chain.proceed(reqBuilder.build());
            }
        });
        httpClientBuilder
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        Retrofit retrofit = builder.client(httpClientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }
}