package com.zzivi.sodexo.base.datasource;

import com.zzivi.sodexo.base.datasource.api.retrofit.ApiRetrofit;
import com.zzivi.sodexo.base.datasource.sharedpreferences.SessionDataSource;
import com.zzivi.sodexo.base.datasource.sharedpreferences.imp.SessionDataSourceSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 28/09/14.
 */
@Module(complete = false, library = true)
public class GlobalDataSourceModule {


    @Provides
    @Singleton
    public SessionDataSource provideLoginSharedPreferences(SessionDataSourceSharedPreferences loginCookie) {
        return loginCookie;
    }

    @Provides
    @Singleton
    public ApiRetrofit provideApiRetrofit(SessionDataSourceSharedPreferences loginCookie) {
        return new ApiRetrofit(loginCookie);
    }
}
