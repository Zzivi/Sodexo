package com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences;

import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;

/**
 * Created by daniel on 28/09/14.
 */
public interface LoginCookieDataSource {
    public void storeLoginCookie(HttpUrlResultModel httpUrlResultModel);

    public HttpUrlResultModel obtainLoginCookie();

}
