package com.zzivi.sodexo.login.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.Navigation;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.base.view.fragment.BaseFragment;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;
import com.zzivi.sodexo.login.view.controller.LoginController;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by romina.liuzzi on 22/08/14.
 */
public class LoginFragment extends BaseFragment implements LoginController.View {

    @Inject
    LoginController loginController;

    @InjectView(R.id.et_login_user)
    EditText username;

    @InjectView(R.id.et_login_password)
    EditText password;

    @InjectView(R.id.bt_login)
    Button buttonLogin;

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @InjectView(R.id.checkbox_recordar)
    CheckBox recordar;

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
        buttonLogin.setEnabled(false);
        loginController.home();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginController.setView(this);
        fillCredentials();
    }

    public void fillCredentials(){
        Navigation navigation = ((BaseActivity) getActivity()).getNavigation();
        LoginCredentials credentials = navigation.getCredentials();
        if (credentials.isStoreCredentials()) {
            username.setText(credentials.getUsername());
            password.setText(credentials.getPassword());
            recordar.setChecked(true);
        }
    }

    @Override
    public void loginError(int message) {
        progressBar.setVisibility(View.GONE);
        buttonLogin.setEnabled(true);
        Toast toast = Toast.makeText(this.getActivity(), getString(message), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public void homeSuccess() {
        buttonLogin.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess() {
        loginController.openCardsBalance(getActivity());
    }

    @SuppressWarnings("unused")  // ButterKnife injected
    @OnClick(R.id.bt_login)
    public void doLogin() {
        if(username.getText().length()!=0) {
            if (password.getText().length() != 0) {
                buttonLogin.setEnabled(false);
                //progressBar.setIndeterminate(true);
                progressBar.setVisibility(View.VISIBLE);
                loginController.login(username.getText().toString(),
                    password.getText().toString(), recordar.isChecked());
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
        password.setHint(R.string.password_hint);
        password.setHintTextColor(Color.GRAY);
    }
}
