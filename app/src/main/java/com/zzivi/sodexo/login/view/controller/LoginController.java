package com.zzivi.sodexo.login.view.controller;

import android.app.Activity;
import android.content.Intent;

import com.zzivi.sodexo.cardsbalance.view.activity.phone.CardsBalanceActivity;
import com.zzivi.sodexo.login.domain.callback.HomeCallback;
import com.zzivi.sodexo.login.domain.callback.LoginCallback;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;
import com.zzivi.sodexo.login.domain.usercase.Home;
import com.zzivi.sodexo.login.domain.usercase.Login;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginController {
    private Login login;
    private Home home;
    private View view;
    private static final String LOGTAG = "LoginController";

    public LoginController(Login login, Home home) {
        this.login = login;
        this.home = home;
    }

    private LoginCallback loginCompleteCallback = new LoginCallback() {
        @Override
        public void loginComplete() {
            view.loginSuccess();
        }
        @Override
        public void onError(int message){
            view.loginError(message);
        }
    };

    private HomeCallback homeCompleteCallback = new HomeCallback() {
        @Override
        public void complete() {
            view.homeSuccess();
        }
    };

    public void home(){
        home.home(homeCompleteCallback);
    }

    public void login (LoginCredentials credentials) {
        login.login(credentials, loginCompleteCallback );
    }

    public void login (String username, String password, boolean storeCredentials){
        LoginCredentials credentials = new LoginCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setStoreCredentials(storeCredentials);
        this.login(credentials);
    }

    public void openCardsBalance(Activity context) {
        Intent intent = new Intent(context, CardsBalanceActivity.class);
        context.startActivity(intent);
    }


    public void setView(View view) {
        this.view = view;
    }

    public interface View {
        void loginSuccess();
        void homeSuccess();
        void loginError(int message);
    }
}
