package com.zzivi.sodexo.base.datasource;

import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.LoginCookieDataSource;
import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.imp.LoginCookieDataSourceSharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 28/09/14.
 */
@Module(complete = false, library = true)
public class GlobalDataSourceModule {


    @Provides
    public LoginCookieDataSource provideLoginSharedPreferences(LoginCookieDataSourceSharedPreferences loginCookie) {
        return loginCookie;
    }
}
