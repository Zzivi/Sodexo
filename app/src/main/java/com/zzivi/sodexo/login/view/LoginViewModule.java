package com.zzivi.sodexo.login.view;

import com.zzivi.sodexo.login.domain.usercase.Home;
import com.zzivi.sodexo.login.domain.usercase.Login;
import com.zzivi.sodexo.login.view.activity.phone.LoginActivity;
import com.zzivi.sodexo.login.view.controller.LoginController;
import com.zzivi.sodexo.login.view.fragment.LoginFragment;

import dagger.Module;
import dagger.Provides;


/**
 * Created by daniel on 27/09/14.
 */
@Module(injects = { LoginActivity.class, LoginFragment.class}, complete = false, library = true)
public class LoginViewModule {

    @Provides
    public LoginController provideLoginController(Login login, Home home){
        return new LoginController(login, home);
    }
}
