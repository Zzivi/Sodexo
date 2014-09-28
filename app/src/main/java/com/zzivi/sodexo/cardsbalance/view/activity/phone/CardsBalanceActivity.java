package com.zzivi.sodexo.cardsbalance.view.activity.phone;

import android.os.Bundle;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.cardsbalance.view.fragment.CardsBalanceFragment;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceActivity extends BaseActivity {

    private CardsBalanceFragment cardsBalanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            cardsBalanceFragment = new CardsBalanceFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, cardsBalanceFragment)
                    .commit();
        }
    }
}
