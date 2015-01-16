package com.zzivi.sodexo.base.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.zzivi.sodexo.base.datasource.GlobalDataSourceModule;
import com.zzivi.sodexo.base.domain.GlobalDomainModule;
import com.zzivi.sodexo.base.utils.module.AndroidModule;
import com.zzivi.sodexo.base.view.GlobalViewModule;
import com.zzivi.sodexo.cardsbalance.datasource.CardsBalanceDataSourceModule;
import com.zzivi.sodexo.cardsbalance.domain.CardsBalanceDomainModule;
import com.zzivi.sodexo.cardsbalance.view.CardsBalanceViewModule;
import com.zzivi.sodexo.login.datasource.LoginDataSourceModule;
import com.zzivi.sodexo.login.domain.LoginDomainModule;
import com.zzivi.sodexo.login.view.LoginViewModule;

import io.fabric.sdk.android.Fabric;
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
        Fabric.with(this, new Crashlytics());
        graph = ObjectGraph.create(getModules().toArray());
        graph.injectStatics();
    }

    protected List<Object> getModules() {
        return Arrays.asList(new AndroidModule(this), new GlobalDomainModule(), new GlobalDataSourceModule(), new GlobalViewModule(),
                new LoginViewModule(), new LoginDomainModule(), new LoginDataSourceModule(),
                new CardsBalanceViewModule(), new CardsBalanceDomainModule(), new CardsBalanceDataSourceModule());
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}
