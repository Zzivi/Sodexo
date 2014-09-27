package com.zzivi.sodexo.login.domain.usercase.impl;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
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

    private static final String LOGTAG = "LoginJob";

    @Inject
    protected LoginJob(JobManager jobManager, MainThread mainThread, LoginDataSource loginDataSource) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY));
        this.loginDataSource = loginDataSource;
    }

    @Override
    public void login(LoginCredentials loginCredentials, LoginCallback callback) {
        this.loginCredentials = loginCredentials;
        this.callback = callback;
        jobManager.addJob(this);
    }

    @Override
    public void doRun() throws Throwable {
        //try {
            loginDataSource.getLegalNumber(loginCredentials);
            System.out.println("Loginnnnnnn");
            notifyLoginComplete(true);
        //}catch (ApiGeneralErrorException e){
        //   domainErrorHandler.notifyError(new GeneralErrorEvent(e.getMessage()));
        //}
    }

    private void notifyLoginComplete(final boolean loginOk) {
        sendCallback(new Runnable() {
            @Override public void run() {
                if(loginOk) {
                    callback.loginComplete();
                }
            }
        });
    }
}
