package com.zzivi.sodexo.login.datasource;

import com.zzivi.sodexo.login.datasource.api.HomeApi;
import com.zzivi.sodexo.login.datasource.api.LoginApi;
import com.zzivi.sodexo.login.datasource.api.retrofit.HomeApiRetrofit;
import com.zzivi.sodexo.login.datasource.api.retrofit.LoginApiRetrofit;
import com.zzivi.sodexo.login.datasource.imp.HomeDataSourceFromApiAndSharedPreferences;
import com.zzivi.sodexo.login.datasource.imp.LoginDataSourceFromApiAndSharedPreferences;
import com.zzivi.sodexo.login.domain.mapper.LoginMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 27/09/14.
 */
@Module(complete = false, library = true)
public class LoginDataSourceModule {

    @Provides
    @Singleton
    public LoginDataSource provideLoginDataSource(LoginDataSourceFromApiAndSharedPreferences loginDataSource) {
        return loginDataSource;
    }

    @Provides
    @Singleton
    public HomeDataSource provideHomeDataSource(HomeDataSourceFromApiAndSharedPreferences homeDataSource) {
        return homeDataSource;
    }


    @Provides
    public LoginMapper provideLoginMapper() { return new LoginMapper(); }

    @Provides
    public LoginApi provideLoginApi(LoginApiRetrofit loginApiRetrofit) {
        return loginApiRetrofit;
    }

    @Provides
    public HomeApi provideHomeApi(HomeApiRetrofit homeApiRetrofit) {
        return homeApiRetrofit;
    }
}
