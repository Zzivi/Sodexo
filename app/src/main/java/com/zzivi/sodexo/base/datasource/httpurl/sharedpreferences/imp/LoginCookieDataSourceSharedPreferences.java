package com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.imp;

import android.content.Context;
import android.content.SharedPreferences;

import com.zzivi.sodexo.base.daggerutils.ForApplication;
import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.LoginCookieDataSource;
import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;

import java.util.ArrayList;
import java.util.List;

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
    public void storeLoginCookie(HttpUrlResultModel httpUrlResultModel) {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        List<String>  cookies =  httpUrlResultModel.getCookies();
        if (cookies != null) {
            int i= 0;
            for (String cookie : cookies) {
                editor.putString("cookie" + i, cookie);
                i++;
            }
        }
    }

    @Override
    public HttpUrlResultModel obtainLoginCookie() {
        SharedPreferences settings = context.getSharedPreferences(AUTHORIZE_FILE, context.MODE_PRIVATE);
        List<String> cookies= new ArrayList<String>();
        //TODO: get all the cookies
        cookies.add(settings.getString("cookie0", ""));
        HttpUrlResultModel httpUrlResultModel = new HttpUrlResultModel();
        httpUrlResultModel.setCookies(cookies);
        return httpUrlResultModel;
    }
}
