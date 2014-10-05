package com.zzivi.sodexo.login.view.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.fragment.BaseFragment;
import com.zzivi.sodexo.login.view.controller.LoginController;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by romina.liuzzi on 22/08/14.
 */
public class LoginFragment extends BaseFragment implements LoginController.View{

    @Inject
    LoginController controller;

    @InjectView(R.id.et_login_user)
    EditText username;

    @InjectView(R.id.et_login_password)
    EditText password;

    @InjectView(R.id.bt_login)
    Button buttonLogin;

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    private View rootView;

	public LoginFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
	    rootView = inflater.inflate(R.layout.fragment_login, container, false);
        return rootView;
	}

    @Override
    public void onResume(){
        super.onResume();
        buttonLogin.setEnabled(true);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
    }

    @Override
    public void loginError() {
    }

    @Override
    public void loginSuccess() {
        controller.openCardsBalance(getActivity());
    }

    @SuppressWarnings("unused")  // ButterKnife injected
    @OnClick(R.id.bt_login)
    public void doLogin() {
        if(username.getText().length()!=0) {
            if (password.getText().length() != 0) {
                buttonLogin.setEnabled(false);
                //progressBar.setIndeterminate(true);
                progressBar.setVisibility(View.VISIBLE);
                controller.login(username.getText().toString(), password.getText().toString());
            } else {
                // Show Error
                password.setHint(R.string.password_warning_hint);
                password.setHintTextColor(Color.RED);
            }
        } else {
            // Show Error
            username.setHint(R.string.login_warning_hint);
            username.setHintTextColor(Color.RED);
        }
    }
    @OnClick(R.id.et_login_user)
    public void resetErrorUser(){
        username.setHint(R.string.login_hint);
        username.setHintTextColor(Color.GRAY);
    }

    @OnClick(R.id.et_login_password)
    public void resetErrorPassword(){
        password.setHint(R.string.login_hint);
        password.setHintTextColor(Color.GRAY);
    }
}
