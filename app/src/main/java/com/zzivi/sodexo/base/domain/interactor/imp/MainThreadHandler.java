package com.zzivi.sodexo.base.domain.interactor.imp;

import android.os.Handler;
import android.os.Looper;

import com.zzivi.sodexo.base.domain.interactor.MainThread;

import javax.inject.Inject;

/**
 * Created by daniel on 27/09/14.
 */
public class MainThreadHandler implements MainThread {
    private Handler handler;

    @Inject
    MainThreadHandler() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
