package com.target.dealbrowserpoc.di;

import com.target.dealbrowserpoc.BuildConfig;
import com.target.dealbrowserpoc.MainPresenter;
import com.target.dealbrowserpoc.data.DealsEndpoints;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger 2 IOC containers for all App level dependencies.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Interceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    DealsEndpoints provideDealsEndpoints(Retrofit retrofit) {
        return retrofit.create(DealsEndpoints.class);
    }

    @Provides
    MainPresenter provideMainPresenter(DealsEndpoints dealsEndpoints) {
        return new MainPresenter(dealsEndpoints);
    }
}
