package io.ideaction.raelsy.screens.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.ideaction.raelsy.R;

public class BuySellFragment extends Fragment {
    private static final String TAG = "BuySellFragment";

    private Context context;

    public static BuySellFragment newInstance() {
        return new BuySellFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.fragment_buy_sell, null);
    }

}
