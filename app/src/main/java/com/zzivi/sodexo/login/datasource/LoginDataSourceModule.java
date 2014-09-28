package com.zzivi.sodexo.login.datasource;

import com.zzivi.sodexo.login.datasource.httpurl.LoginHttpUrl;
import com.zzivi.sodexo.login.datasource.httpurl.imp.LoginHttpUrlConnection;
import com.zzivi.sodexo.login.datasource.imp.LoginDataSourceFromUrl;
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
    public LoginDataSource provideLoginDataSource(LoginDataSourceFromUrl loginDataSource) {
        return loginDataSource;
    }

    @Provides
    public LoginMapper provideLoginMapper() { return new LoginMapper(); }

    @Provides
    public LoginHttpUrl provideLoginHttpUrl(LoginHttpUrlConnection loginHttpUrlConnection) { return loginHttpUrlConnection; }
}
