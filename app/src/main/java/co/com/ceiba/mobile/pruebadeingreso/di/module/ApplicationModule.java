package co.com.ceiba.mobile.pruebadeingreso.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.data.CeibaDataBase;
import co.com.ceiba.mobile.pruebadeingreso.data.Repository;
import co.com.ceiba.mobile.pruebadeingreso.data.RepositoryInterface;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiService;
import co.com.ceiba.mobile.pruebadeingreso.rest.LoggingInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.REQUEST_TIMEOUT;
import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.URL_BASE;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Executor providesExecutor() {
        return Executors.newSingleThreadExecutor();
    }


    @Provides
    @Singleton
    Repository provideRepository(ApiService apiService, Executor executor, UserDao userDao, PostDao postDao) {
        return new Repository(apiService, executor, userDao, postDao);
    }

    @Provides
    @Singleton
    UserDao providesUserDao(CeibaDataBase dataBase) {
        return dataBase.userDao();
    }

    @Provides
    @Singleton
    PostDao providePostDao(CeibaDataBase dataBase) {
        return dataBase.postDao();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder().connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }


    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(OkHttpClient.Builder okHttpClientBuilder) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        okHttpClientBuilder.addNetworkInterceptor(new LoggingInterceptor());
        okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor());

        return okHttpClientBuilder.cache(null).build();

    }


    @Provides
    @Singleton
    static Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL_BASE)
                .build();
    }

    @Provides
    @Singleton
    ApiService provideService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


}
