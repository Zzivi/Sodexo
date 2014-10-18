package com.zzivi.sodexo.base.view;

import android.content.Intent;
import android.util.Log;

import com.zzivi.sodexo.base.datasource.sharedpreferences.LoginCookieDataSource;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.cardsbalance.view.activity.phone.CardsBalanceActivity;
import com.zzivi.sodexo.login.view.activity.phone.LoginActivity;

/**
 * Created by daniel on 17/10/14.
 */
public class Navigation {
    public static final String LOGTAG = "BaseActivity";
    public final LoginCookieDataSource loginCookieDataSource;

    public Navigation(LoginCookieDataSource loginCookieDataSource) {
        this.loginCookieDataSource = loginCookieDataSource;

    }
    /**
     * Check login method will check user login status
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

    /**
     * Check USer is logged
     *
     * @return true if user is Logged, else otherwise
     */
    public boolean isLoggedIn(){
        return this.loginCookieDataSource.isLoggedIn();
    }
}
