package com.zzivi.sodexo.cardsbalance.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.base.view.activity.BaseActivity;
import com.zzivi.sodexo.base.view.fragment.BaseFragment;
import com.zzivi.sodexo.cardsbalance.view.adapter.CardBalanceListAdapter;
import com.zzivi.sodexo.cardsbalance.view.controller.CardsBalanceController;
import com.zzivi.sodexo.cardsbalance.view.mapper.CardBalanceItemMapper;
import com.zzivi.sodexo.cardsbalance.view.model.CardBalanceItem;
import com.zzivi.sodexo.login.view.activity.phone.LoginActivity;

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
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

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
        showCardsBalance();
    }

    public void showCardsBalance(){
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        controller.showCardsBalance();
    }

    @Override
    public void showComplete() {
        List<CardBalanceItem> cardsBalances = cardBalanceMapper.transform(controller.getListCardsBalance());
        progressBar.setVisibility(View.INVISIBLE);
        if (cardsBalances.isEmpty()) {
            zeroFound.setVisibility(View.VISIBLE);
            zeroFound.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
            redirectToLogin(getActivity());
            getActivity().finish();
        } else {
            cards.setAdapter(new CardBalanceListAdapter(getActivity(), cardsBalances));
            zeroFound.setVisibility(View.GONE);
        }

    }

    public void redirectToLogin(Activity context) {
        ((BaseActivity) context).getNavigation().cleanCredentials();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
