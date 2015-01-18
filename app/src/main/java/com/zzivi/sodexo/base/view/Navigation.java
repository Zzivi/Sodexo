package com.zzivi.sodexo.base.view;

import android.content.Intent;
import android.util.Log;

import com.zzivi.sodexo.BuildConfig;
import com.zzivi.sodexo.base.datasource.sharedpreferences.SessionDataSource;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.cardsbalance.view.activity.phone.CardsBalanceActivity;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;
import com.zzivi.sodexo.login.view.activity.phone.LoginActivity;

/**
 * Created by daniel on 17/10/14.
 */
public class Navigation {
    public static final String LOGTAG = "BaseActivity";
    public final SessionDataSource loginCookieDataSource;

    public Navigation(SessionDataSource loginCookieDataSource) {
        this.loginCookieDataSource = loginCookieDataSource;

    }
    /**
     * Check login method will check user login statusLoginCookieDataSource
     * If false it will redirect user to login page
     * Else won't do anything
     *
     */
    public void checkLogin(BaseActivity originActivity){

        if((originActivity instanceof LoginActivity) &&
                isLoggedIn()) {
            Log.d(LOGTAG, "user is logged in, redirect to Card Balance activity");
            Intent intent = new Intent(originActivity.getBaseContext(), CardsBalanceActivity.class);
            originActivity.startActivity(intent);
            originActivity.finish();
        }
    }

    public void cleanCredentials(){
        loginCookieDataSource.setLoggedIn(false);
        loginCookieDataSource.deleteLoginCookie();
    }

    public LoginCredentials getCredentials(){
        return loginCookieDataSource.obtainCredentials();
    }

    /**
     * Check USer is logged
     *
     * @return true if user is Logged, else otherwise
     */
    public boolean isLoggedIn(){
        return this.loginCookieDataSource.isLoggedIn();
    }
}
