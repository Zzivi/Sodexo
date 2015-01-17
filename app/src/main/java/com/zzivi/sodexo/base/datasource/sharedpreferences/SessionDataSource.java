package com.zzivi.sodexo.base.datasource.sharedpreferences;

import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

/**
 * Created by daniel on 28/09/14.
 */
public interface SessionDataSource {
    public void storeLoginCookie(CookiesResultModel cookiesResultModel);

    public void deleteLoginCookie ();

    public CookiesResultModel obtainLoginCookie();

    public boolean isLoggedIn();

    public void setLoggedIn(boolean isLogged);

    public void storeCredentials(LoginCredentials LoginCredentials);

    public LoginCredentials obtainCredentials();

    public void removeCredentials();
}
