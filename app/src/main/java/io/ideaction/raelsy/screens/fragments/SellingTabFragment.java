package io.ideaction.raelsy.screens.fragments;

import android.app.Fragment;
import android.widget.Button;
import android.widget.ExpandableListView;

import io.ideaction.raelsy.adapters.SellingListAdapter;

public class SellingTabFragment extends Fragment {
    private static final String TAG = "SellingTabFragment";

    // UI Components
    private Button sellingCreateNewListingButton;
    private Button sellingModifyComparablesButton;

    // List Properties
    private ExpandableListView sellingPropertiesExpandableListView;
    private SellingListAdapter adapter;

}
