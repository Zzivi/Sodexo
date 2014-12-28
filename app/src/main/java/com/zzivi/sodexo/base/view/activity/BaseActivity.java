package com.zzivi.sodexo.base.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.zzivi.sodexo.base.application.BaseApplication;
import com.zzivi.sodexo.base.view.Navigation;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by daniel on 27/09/14.
 */
public class BaseActivity extends ActionBarActivity {

    @Inject
    protected Navigation navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
        ButterKnife.inject(this);
        navigation.checkLogin(this);
    }

    public void inject(Object object) {
        // Perform injection so that when this call returns all dependencies will be available
        // for use.
        ((BaseApplication) getApplication()).inject(object);
    }

    public Navigation getNavigation (){
        return navigation;
    }
}