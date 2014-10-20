package com.zzivi.sodexo.login.view.controller;

import com.zzivi.sodexo.login.domain.callback.HomeCallback;
import com.zzivi.sodexo.login.domain.usercase.Home;

/**
 * Created by daniel on 27/09/14.
 */
public class HomeController {
    private Home home;
    private View view;
    private static final String LOGTAG = "HomeController";

    public HomeController(Home home) {
        this.home = home;
    }

    private HomeCallback homeCompleteCallback = new HomeCallback() {
        @Override
        public void complete() {
            view.homeSuccess();
        }
        //@Override
        //public void onError(int message){
        //    view.loginError(message);
        //}
    };

    public void home(){
        home.home(homeCompleteCallback);
    }

    public void setView(View view) {
        this.view = view;
    }

    public interface View {
        void homeSuccess();
        //void loginError(int message);
    }
}
