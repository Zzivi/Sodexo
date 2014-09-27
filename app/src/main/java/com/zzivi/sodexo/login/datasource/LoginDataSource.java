package com.zzivi.sodexo.login.datasource;

import com.zzivi.sodexo.login.domain.model.LoginCredentials;

/**
 * Created by daniel on 27/09/14.
 */
public interface LoginDataSource {
    boolean getLegalNumber(LoginCredentials loginCredentials);
}
