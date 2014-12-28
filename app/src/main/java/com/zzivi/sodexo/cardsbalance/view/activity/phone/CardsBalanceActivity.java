package com.zzivi.sodexo.cardsbalance.view.activity.phone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.cardsbalance.view.controller.CardsBalanceController;
import com.zzivi.sodexo.cardsbalance.view.fragment.CardsBalanceFragment;

import javax.inject.Inject;

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
                //Intent intent = new Intent(this, CardsBalanceActivity.class);
                //this.startActivity(intent);
                //this.finish();
                cardsBalanceFragment.showCardsBalance();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
