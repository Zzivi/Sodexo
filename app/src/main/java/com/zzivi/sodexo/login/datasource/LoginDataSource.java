package com.zzivi.sodexo.login.datasource;

import com.zzivi.sodexo.login.domain.model.LoginCredentials;

import java.io.IOException;

/**
 * Created by daniel on 27/09/14.
 */
public interface LoginDataSource {
    boolean getCookies(LoginCredentials loginCredentials) throws IOException;
}
