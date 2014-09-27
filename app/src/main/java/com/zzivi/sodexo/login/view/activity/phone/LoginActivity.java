package com.zzivi.sodexo.login.view.activity.phone;

import android.os.Bundle;

import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.R;
import com.zzivi.sodexo.login.view.fragment.LoginFragment;


public class LoginActivity extends BaseActivity {

    private LoginFragment loginFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		if (savedInstanceState == null) {
            loginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction()
					.add(R.id.container, loginFragment)
					.commit();
		}
	}

}
