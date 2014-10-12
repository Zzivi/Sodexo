package com.zzivi.sodexo.base.datasource.sharedpreferences;

import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;

/**
 * Created by daniel on 28/09/14.
 */
public interface LoginCookieDataSource {
    public void storeLoginCookie(CookiesResultModel cookiesResultModel);

    public CookiesResultModel obtainLoginCookie();

}
