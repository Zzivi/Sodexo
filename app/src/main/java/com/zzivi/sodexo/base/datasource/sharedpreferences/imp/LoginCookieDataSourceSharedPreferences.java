package com.zzivi.sodexo.base.datasource.sharedpreferences.imp;

import android.content.Context;
import android.content.SharedPreferences;

import com.zzivi.sodexo.base.daggerutils.ForApplication;
import com.zzivi.sodexo.base.datasource.sharedpreferences.LoginCookieDataSource;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;

import javax.inject.Inject;

/**
 * Created by daniel on 28/09/14.
 */
public class LoginCookieDataSourceSharedPreferences implements LoginCookieDataSource {
    private static final String AUTHORIZE_FILE = "AUTHORIZE";

    public final Context context;

    @Inject
    public LoginCookieDataSourceSharedPreferences(@ForApplication Context context){
        this.context = context;
    }

    @Override
    public void storeLoginCookie(CookiesResultModel cookiesResultModel) {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        String cookie = cookiesResultModel.getCookie();
        if (cookie != null) {
             editor.putString("cookie", cookie);
        }
        editor.commit();
    }

    @Override
    public CookiesResultModel obtainLoginCookie() {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);

        CookiesResultModel cookiesResultModel = new CookiesResultModel();
        cookiesResultModel.setCookie(settings.getString("cookie", "not found"));
        return cookiesResultModel;
    }

    @Override
    public boolean isLoggedIn() {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        return settings.getBoolean("isLoggedIn", false);
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.commit();
    }
}
