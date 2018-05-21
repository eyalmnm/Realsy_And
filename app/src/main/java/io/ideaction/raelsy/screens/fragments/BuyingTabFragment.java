package io.ideaction.raelsy.screens.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.adapters.BuyingListAdapter;
import io.ideaction.raelsy.models.BuyingItem;

public class BuyingTabFragment extends Fragment {
    private static final String TAG = "BuyingTabFragment";

    // UI Components
    private ExpandableListView buyingPropertiesExpandableListView;

    private ArrayList<ArrayList<BuyingItem>> propertiesLists;
    private BuyingListAdapter adapter;
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

        propertiesLists = new ArrayList<>(2);

        // Invoke references to UI components
        buyingPropertiesExpandableListView = view.findViewById(R.id.buyingPropertiesExpandableListView);

        // Init Buying List
        adapter = new BuyingListAdapter(context, propertiesLists);
        buyingPropertiesExpandableListView.setAdapter(adapter);

        // Expand all children
        for(int i=0; i < adapter.getGroupCount(); i++) {
            buyingPropertiesExpandableListView.expandGroup(i);
        }

        // Make the list un - collapsible
        buyingPropertiesExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,int  groupPosition, long id) {
                return true;
            }
        });

        // Check permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermissions()) {
                requestPermission();
            } else {
                continueAppLoading();
            }
        } else {
            continueAppLoading();
        }

    }

    private void continueAppLoading() {
        ArrayList<BuyingItem> sharedProperties = getSharedProperties();
        ArrayList<BuyingItem> favoriteProperties = getFavoriteProperties();
        propertiesLists.clear();
        propertiesLists.add(0, sharedProperties);
        propertiesLists.add(1, favoriteProperties);
        new Handler(getActivity().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
