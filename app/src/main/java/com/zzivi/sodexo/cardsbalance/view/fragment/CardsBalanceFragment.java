package com.zzivi.sodexo.cardsbalance.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.fragment.BaseFragment;
import com.zzivi.sodexo.cardsbalance.view.adapter.CardBalanceListAdapter;
import com.zzivi.sodexo.cardsbalance.view.controller.CardsBalanceController;
import com.zzivi.sodexo.cardsbalance.view.mapper.CardBalanceItemMapper;
import com.zzivi.sodexo.cardsbalance.view.model.CardBalanceItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceFragment extends BaseFragment implements CardsBalanceController.View{

    @InjectView(R.id.cardsList)
    ListView cards;
    @InjectView(R.id.zerocase)
    View zeroFound;

    @Inject
    CardsBalanceController controller;
    @Inject
    CardBalanceItemMapper cardBalanceMapper;

    private View rootView;

    public CardsBalanceFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cardsbalancelist, container, false);
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
        List<CardBalanceItem> cardsBalances = cardBalanceMapper.transform(controller.getListCardsBalance());

        if (cardsBalances.isEmpty()) {
            zeroFound.setVisibility(View.VISIBLE);
            zeroFound.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
        } else {
            cards.setAdapter(new CardBalanceListAdapter(getActivity(), cardsBalances));
            zeroFound.setVisibility(View.GONE);
        }

    }
}
