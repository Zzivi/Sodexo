package com.zzivi.sodexo.login.datasource.api;

import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;
import com.zzivi.sodexo.login.datasource.api.model.LoginRequestApiModel;

/**
 * Created by daniel on 11/10/14.
 */
public interface LoginApi {
    public boolean doLogin(LoginRequestApiModel credentials);
}
