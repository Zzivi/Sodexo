package com.zzivi.sodexo.login.domain.callback;

/**
 * Created by daniel on 27/09/14.
 */
public interface LoginCallback {
    void loginComplete();
    void onError(int message);
}
