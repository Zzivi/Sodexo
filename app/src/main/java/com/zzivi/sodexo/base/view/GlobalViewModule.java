package com.zzivi.sodexo.base.view;

import com.zzivi.sodexo.base.datasource.sharedpreferences.imp.SessionDataSourceSharedPreferences;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.base.view.fragment.AdFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 12/10/14.
 */
@Module(injects = {BaseActivity.class, AdFragment.class}, complete = false,
        library = true)
public class GlobalViewModule {

    @Provides
    @Singleton
    Navigation provideNavigation(SessionDataSourceSharedPreferences loginCookieDataSourceSharedPreferences){
        return new Navigation(loginCookieDataSourceSharedPreferences);
    }
}