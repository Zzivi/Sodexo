package com.zzivi.sodexo.cardsbalance.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.fragment.BaseFragment;
import com.zzivi.sodexo.cardsbalance.datasource.model.CardBalanceResultModel;
import com.zzivi.sodexo.cardsbalance.view.controller.CardsBalanceController;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceFragment extends BaseFragment implements CardsBalanceController.View{

    @InjectView(R.id.tv_cardbalance)
    TextView cardbalance;

    @Inject
    CardsBalanceController controller;

    private View rootView;

    public CardsBalanceFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cardsbalance, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        controller.showCardsBalance();
    }

    @Override
    public void showComplete() {
        List<CardBalanceResultModel> cardsBalances = controller.getListCardsBalance();
        cardbalance.setText(cardsBalances.get(0).getCardName());
    }

}
