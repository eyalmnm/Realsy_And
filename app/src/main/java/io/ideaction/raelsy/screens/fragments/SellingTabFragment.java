package io.ideaction.raelsy.screens.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.adapters.SellingListAdapter;
import io.ideaction.raelsy.models.SellingItem;
import io.ideaction.raelsy.network.RetrofitConnectionManager;
import io.ideaction.raelsy.network.models.SellingItemModel;
import io.ideaction.raelsy.network.models.SellingItemsResponse;
import io.ideaction.raelsy.utils.PreferencesUtils;
import io.ideaction.raelsy.utils.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellingTabFragment extends Fragment {
    private static final String TAG = "SellingTabFragment";

    private static final boolean UN_COLLAPSABLE = false;

    // UI Components
    private Button sellingCreateNewListingButton;
    private Button sellingModifyComparableButton;
    private ExpandableListView sellingPropertiesExpandableListView;

    // List Properties
    private ArrayList<ArrayList<SellingItem>> propertiesLists;
    private SellingListAdapter adapter;
    private Context context;


    private String myClientID;

    public static SellingTabFragment newInstance() {
        return new SellingTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        myClientID = PreferencesUtils.getInstance(context).getMyClientId();
        return inflater.inflate(R.layout.fragment_selling_tab, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        propertiesLists = new ArrayList<>(2);

        // Invoke References to UI Components
        sellingCreateNewListingButton = view.findViewById(R.id.sellingCreateNewListingButton);
        sellingModifyComparableButton = view.findViewById(R.id.sellingModifyComparableButton);
        sellingPropertiesExpandableListView = view.findViewById(R.id.sellingPropertiesExpandableListView);

        // Init Selling List
        adapter = new SellingListAdapter(context, propertiesLists);
        sellingPropertiesExpandableListView.setAdapter(adapter);

        // Make the list un - collapsible
        if (true == UN_COLLAPSABLE) {
            sellingPropertiesExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });
        }

        // Init Buttons
        sellingCreateNewListingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Do Something here
            }
        });
        sellingModifyComparableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Do Something here
            }
        });

        // InitLists
        propertiesLists.clear();
        getActiveItems();
        getComparableItems();
    }

    private void getComparableItems() {
        if (true == StringUtils.isNullOrEmpty(myClientID)) {
            // TODO go to login screen
        } else {
            RetrofitConnectionManager.getInstance().getComparableItems(myClientID, new Callback<SellingItemsResponse>() {
                @Override
                public void onResponse(Call<SellingItemsResponse> call, Response<SellingItemsResponse> response) {
                    Log.d(TAG, "getComparableItems -> onResponse");
                    List<SellingItemModel> items = response.body().getResults();
                    propertiesLists.add(1, getSellingItems(items));
                    refreshList();
                }

                @Override
                public void onFailure(Call<SellingItemsResponse> call, Throwable t) {
                    Log.e(TAG, "getComparableItems -> onFailure", t);
                    showMessage(t.getMessage());
                }
            });
        }
    }

    private void getActiveItems() {
        if (true == StringUtils.isNullOrEmpty(myClientID)) {
            // TODO go to login screen
        } else {
            RetrofitConnectionManager.getInstance().getActiveListingItems(myClientID, new Callback<SellingItemsResponse>() {
                @Override
                public void onResponse(Call<SellingItemsResponse> call, Response<SellingItemsResponse> response) {
                    Log.d(TAG, "getActiveItems -> onResponse");
                    List<SellingItemModel> items = response.body().getResults();
                    propertiesLists.add(0, getSellingItems(items));
                    refreshList();
                }

                @Override
                public void onFailure(Call<SellingItemsResponse> call, Throwable t) {
                    Log.e(TAG, "getActiveItems -> onFailure", t);
                    showMessage(t.getMessage());
                }
            });
        }
    }

    private ArrayList<SellingItem> getSellingItems(List<SellingItemModel> items) {
        ArrayList<SellingItem> buyingItems = new ArrayList<>();
        if (null != items) {
            for (SellingItemModel item : items) {
                buyingItems.add(new SellingItem(item));
            }
        }
        return buyingItems;
    }

    private void refreshList() {
        new Handler(getActivity().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void showMessage(final String message) {
        new Handler(getActivity().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
