package io.ideaction.raelsy.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.models.BuyingItem;

public class BuyingListAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "BuyingListAdapter";

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ArrayList<BuyingItem>> buyingItems;

    public BuyingListAdapter(Context context, ArrayList<ArrayList<BuyingItem>> buyingItems) {
        inflater = LayoutInflater.from(context);
        this.buyingItems = buyingItems;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return buyingItems.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return buyingItems.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return buyingItems.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return buyingItems.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (null == view) {
            view = inflater.inflate(R.layout.layout_buying_header, null);
        }
        TextView buyingTagListsHeader = view.findViewById(R.id.buyingTagListsHeader);
        int stringId = (0 == 1) ? R.string.buying_tab_shared_string : R.string.buying_tab_favorit_string;
        buyingTagListsHeader.setText(stringId);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        if (null == view) {
            view = inflater.inflate(R.layout.layout_buying_item, null);
        }

        // Invoke references to UI components
        LinearLayout imagesContainer = view.findViewById(R.id.imagesContainer);
        TextView buyingItemPrice = view.findViewById(R.id.buyingItemPrice);
        TextView buyingItemFullAddress = view.findViewById(R.id.buyingItemFullAddress);
        TextView buyingItemFullDetails = view.findViewById(R.id.buyingItemFullDetails);
        ImageView buyingItemLikeImageView = view.findViewById(R.id.buyingItemLikeImageView);

        // Init UI
        final BuyingItem buyingItem = buyingItems.get(groupPosition).get(childPosition);
        buyingItemPrice.setText("$" + String.valueOf(buyingItem.getPrice()));
        buyingItemFullAddress.setText(context.getString(R.string.buying_item_address_full_address, buyingItem.getAddress(),
                buyingItem.getCity(), buyingItem.getState(), buyingItem.getZipCode()));
        buyingItemFullDetails.setText(context.getString(R.string.buying_item_property_details, buyingItem.getBedRooms(),
                buyingItem.getBathRooms(), buyingItem.getApartmentArea()));
        int likeDrawableId = buyingItem.isLiked() ? R.drawable.like_fill : R.drawable.like_empty;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buyingItemLikeImageView.setImageDrawable(context.getDrawable(likeDrawableId));
        } else {
            buyingItemLikeImageView.setImageDrawable(context.getResources().getDrawable(likeDrawableId));
        }
        buyingItemLikeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyingItem.setLiked(! buyingItem.isLiked());
                BuyingListAdapter.this.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
