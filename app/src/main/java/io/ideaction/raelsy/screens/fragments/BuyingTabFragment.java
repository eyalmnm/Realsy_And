package io.ideaction.raelsy.screens.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.adapters.BuyingListAdapter;
import io.ideaction.raelsy.models.BuyingItem;

public class BuyingTabFragment extends Fragment {
    private static final String TAG = "BuyingTabFragment";

    // UI Components
    private ListView buyingListView;
    private ArrayList<BuyingItem> sharedBuyingItems;
    private BuyingListAdapter sharedItemsAdapter;
    private ArrayList<BuyingItem> favoriteBuyingItems;
    private BuyingListAdapter favoriteItemsAdapter;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.fragment_buying_tab, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ccc
    }
}
