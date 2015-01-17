package com.zzivi.sodexo.login.domain.usercase.impl;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.zzivi.sodexo.base.datasource.api.retrofit.exceptions.ApiUnauthorizedException;
import com.zzivi.sodexo.base.datasource.sharedpreferences.SessionDataSource;
import com.zzivi.sodexo.base.domain.interactor.MainThread;
import com.zzivi.sodexo.base.domain.interactor.imp.UserCaseJob;
import com.zzivi.sodexo.login.datasource.LoginDataSource;
import com.zzivi.sodexo.login.domain.callback.LoginCallback;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;
import com.zzivi.sodexo.login.domain.usercase.Login;

import javax.inject.Inject;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginJob extends UserCaseJob implements Login {
    private LoginCredentials loginCredentials ;
    private LoginCallback callback;
    private LoginDataSource loginDataSource;
    private SessionDataSource sessionDataSource;

    @Inject
    protected LoginJob(JobManager jobManager, MainThread mainThread, LoginDataSource loginDataSource, SessionDataSource sessionDataSource) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY));
        this.loginDataSource = loginDataSource;
        this.sessionDataSource = sessionDataSource;
    }

    @Override
    public void login(LoginCredentials loginCredentials, LoginCallback callback) {
        this.loginCredentials = loginCredentials;
        this.callback = callback;
        jobManager.addJob(this);
    }

    @Override
    public void doRun() throws Throwable {
        try {
            if (loginCredentials.isStoreCredentials()) {
                sessionDataSource.storeCredentials(loginCredentials);
            } else {
                sessionDataSource.removeCredentials();
            }
            loginDataSource.getCookies(loginCredentials);
            notifyLoginComplete(true, 0);
        } catch (ApiUnauthorizedException ex) {
            notifyLoginComplete(false, ex.getLocalizedMessageResource());
        }
    }

    private void notifyLoginComplete(final boolean loginOk, final int message) {
        sendCallback(new Runnable() {
            @Override public void run() {
            if(loginOk) {
                callback.loginComplete();
            } else {
                callback.onError(message);
            }
            }
        });
    }
}
