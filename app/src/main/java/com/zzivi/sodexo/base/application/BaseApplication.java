package com.zzivi.sodexo.base.application;

import android.app.Application;

import com.zzivi.sodexo.base.utils.module.AndroidModule;
import com.zzivi.sodexo.login.view.model.LoginViewModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by daniel on 27/09/14.
 */
public class BaseApplication extends Application {
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(getModules().toArray());
        graph.injectStatics();
    }

    protected List<Object> getModules() {
        return Arrays.asList(new AndroidModule(this), new LoginViewModule());
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}
