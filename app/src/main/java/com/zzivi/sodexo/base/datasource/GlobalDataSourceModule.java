package com.zzivi.sodexo.base.datasource;

import com.zzivi.sodexo.base.datasource.httpurl.request.HttpRequestDataSource;
import com.zzivi.sodexo.base.datasource.httpurl.request.imp.HttpRequestDataSourceImpl;
import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.LoginCookieDataSource;
import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.imp.LoginCookieDataSourceSharedPreferences;

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
    public LoginCookieDataSource provideLoginSharedPreferences(LoginCookieDataSourceSharedPreferences loginCookie) {
        return loginCookie;
    }

    @Provides
    @Singleton
    public HttpRequestDataSource provideHttpRequestDataSource(HttpRequestDataSourceImpl httpRequest) {
        return httpRequest;
    }
}
