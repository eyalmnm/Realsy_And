package io.ideaction.raelsy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.models.BuyingItem;

public class BuyingListAdapter extends BaseAdapter {
    private static final String TAG = "BuyingListAdapter";

    private ArrayList<BuyingItem> buyingItems;
    private Context context;
    private LayoutInflater inflater;

    public BuyingListAdapter(Context context, ArrayList<BuyingItem> buyingItems) {
        inflater = LayoutInflater.from(context);
        this.buyingItems = buyingItems;
        this.context = context;
    }

    public ArrayList<BuyingItem> getBuyingItems() {
        return buyingItems;
    }

    public void setBuyingItems(ArrayList<BuyingItem> buyingItems) {
        this.buyingItems = buyingItems;
    }


    @Override
    public int getCount() {
        return buyingItems.size();
    }

    @Override
    public Object getItem(int i) {
        return buyingItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {
            view = inflater.inflate(R.layout.layout_buying_item, null);
        }

        return view;
    }
}
