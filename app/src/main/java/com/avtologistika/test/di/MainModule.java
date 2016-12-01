package com.avtologistika.test.di;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.avtologistika.test.GlobalApplication;
import com.avtologistika.test.api.HttpEndpointsApi;
import com.avtologistika.test.screens.login.LoginContract;
import com.avtologistika.test.screens.login.LoginPresenter;
import com.avtologistika.test.utils.InMemoryCache;
import com.avtologistika.test.utils.NetworkChecker;
import com.avtologistika.test.utils.NetworkCheckerImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MainModule {

    private final String baseUrl;
    private final GlobalApplication globalApplication;

    public MainModule(String baseUrl, GlobalApplication globalApplication) {
        this.baseUrl = baseUrl;
        this.globalApplication = globalApplication;
    }

    @Provides
    Context getContext() {
        return globalApplication;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    GlobalApplication providesGlobalApplication() {
        return globalApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(GlobalApplication globalApplication) {
        return PreferenceManager.getDefaultSharedPreferences(globalApplication);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    NetworkChecker provideNetworkChecker(Context context) {
        return new NetworkCheckerImpl(context);
    }

    @Provides
    @Singleton
    LoginContract.LoginPresenter provideLoginPresenter(InMemoryCache inMemoryCache, HttpEndpointsApi httpService) {
        return new LoginPresenter(inMemoryCache, httpService);
    }

    @Provides
    @Singleton
    HttpEndpointsApi provideApiEndpointInterface(Retrofit retrofit) {
        return retrofit.create(HttpEndpointsApi.class);
    }

    @Provides
    @Singleton
    InMemoryCache provideInMemoryCache() {
        return  new InMemoryCache();
    }
}