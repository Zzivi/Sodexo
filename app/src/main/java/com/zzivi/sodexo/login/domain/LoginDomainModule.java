package com.zzivi.sodexo.login.domain;

import com.zzivi.sodexo.login.domain.usercase.Login;
import com.zzivi.sodexo.login.domain.usercase.impl.LoginJob;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 27/09/14.
 */
@Module(complete = false, library = true)
public class LoginDomainModule {

    @Provides
    public Login provideLogin(LoginJob loginJob) {
        return loginJob;
    }

}

