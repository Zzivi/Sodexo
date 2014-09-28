package com.zzivi.sodexo.login.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
    }

    @Override
    public void loginError() {
    }

    @Override
    public void loginSuccess() {
       // ((BaseActivity) getActivity()).getNavigation().redirectToOrigin(this.getActivity());
    }

    @SuppressWarnings("unused")  // ButterKnife injected
    @OnClick(R.id.bt_login)
    public void doLogin() {
        if(username.getText().length()!=0) {
            if (password.getText().length() != 0) {
                controller.login(username.getText().toString(), password.getText().toString());
                controller.openCardsBalance(getActivity());
            }
        }
    }
}
