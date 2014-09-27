package com.zzivi.sodexo.base.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zzivi.sodexo.base.view.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Base fragment which performs injection using the activity object graph of its parent.
 */
public abstract class BaseFragment extends Fragment {

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivity) this.getActivity()).inject(this);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }
}
