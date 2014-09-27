package com.zzivi.sodexo.login.domain.usercase;

import com.zzivi.sodexo.login.domain.callback.LoginCallback;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

/**
 * Created by daniel on 27/09/14.
 */
public interface Login {
    void login(LoginCredentials loginCredentials, LoginCallback callback);
}
