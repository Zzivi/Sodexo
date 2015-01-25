package com.zzivi.sodexo.cardsbalance.view.activity.phone;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.base.view.fragment.AdFragment;
import com.zzivi.sodexo.cardsbalance.view.fragment.CardsBalanceFragment;


/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceActivity extends BaseActivity {

    private CardsBalanceFragment cardsBalanceFragment;
    private AdFragment adFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardsbalance);
        if (savedInstanceState == null) {
            cardsBalanceFragment = new CardsBalanceFragment();
            adFragment = new AdFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.balanceFragment, cardsBalanceFragment)
                    .add(R.id.adFragment, adFragment)
                    .commit();
         }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_refresh:
                cardsBalanceFragment.showCardsBalance();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
