package com.zzivi.sodexo.login.view.activity.phone;

import android.app.Activity;
import android.os.Bundle;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.login.view.fragment.LoginFragment;


public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new LoginFragment())
					.commit();
		}
	}

}
