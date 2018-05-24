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
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import io.ideaction.raelsy.R;
import io.ideaction.raelsy.adapters.BuyingListAdapter;
import io.ideaction.raelsy.models.BuyingItem;
import io.ideaction.raelsy.network.RetrofitConnectionManager;
import io.ideaction.raelsy.network.models.BuyingItemModel;
import io.ideaction.raelsy.network.models.BuyingItemsResponse;
import io.ideaction.raelsy.utils.PreferencesUtils;
import io.ideaction.raelsy.utils.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyingTabFragment extends Fragment {
    private static final String TAG = "BuyingTabFragment";

    private static final boolean UN_COLLAPSABLE = false;

    // UI Components
    private ExpandableListView buyingPropertiesExpandableListView;

    private ArrayList<ArrayList<BuyingItem>> propertiesLists;
    private BuyingListAdapter adapter;
    private Context context;

    private String myClientID;


    public static BuyingTabFragment newInstance() {
        return new BuyingTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        myClientID = PreferencesUtils.getInstance(context).getMyClientId();
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
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            buyingPropertiesExpandableListView.expandGroup(i);
        }

        // Make the list un - collapsible
        if (true == UN_COLLAPSABLE) {
            buyingPropertiesExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });
        }

        propertiesLists.clear();
        getSharedItems();
        getFavoriteItems();
    }

    private void getSharedItems() {
        if (true == StringUtils.isNullOrEmpty(myClientID)) {
            // TODO go to login screen
        } else {
            RetrofitConnectionManager.getInstance().getSharedWithMeBuyingItems(myClientID, new Callback<BuyingItemsResponse>() {
                @Override
                public void onResponse(Call<BuyingItemsResponse> call, Response<BuyingItemsResponse> response) {
                    Log.d(TAG, "getSharedWithMeBuyingItems -> onResponse");
                    List<BuyingItemModel> items = response.body().getResults();
                    propertiesLists.add(0, getBuyingItems(items));
                    refreshList();
                }

                @Override
                public void onFailure(Call<BuyingItemsResponse> call, Throwable t) {
                    Log.e(TAG, "getSharedWithMeBuyingItems -> onFailure", t);
                    showMessage(t.getMessage());
                }
            });
        }
    }

    private void getFavoriteItems() {
        RetrofitConnectionManager.getInstance().getMyFavoriteItems(myClientID, new Callback<BuyingItemsResponse>() {
            @Override
            public void onResponse(Call<BuyingItemsResponse> call, Response<BuyingItemsResponse> response) {
                Log.d(TAG, "getMyFavoriteItems -> onResponse");
                List<BuyingItemModel> items = response.body().getResults();
                propertiesLists.add(1, getBuyingItems(items));
                refreshList();
            }

            @Override
            public void onFailure(Call<BuyingItemsResponse> call, Throwable t) {
                Log.e(TAG, "getMyFavoriteItems -> onFailure", t);
                showMessage(t.getMessage());
            }
        });
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

    private ArrayList<BuyingItem> getBuyingItems(List<BuyingItemModel> items) {
        ArrayList<BuyingItem> buyingItems = new ArrayList<>();
        if (null != items) {
            for (BuyingItemModel item : items) {
                buyingItems.add(new BuyingItem(item));
            }
        }
        return buyingItems;
    }

}
