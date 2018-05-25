package io.ideaction.raelsy.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.models.BuyingItem;
import io.ideaction.raelsy.models.SellingItem;

public class SellingListAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "SellingListAdapter";

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ArrayList<SellingItem>> sellingItems;

    public SellingListAdapter(Context context, ArrayList<ArrayList<SellingItem>> sellingItems) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.sellingItems = sellingItems;
    }

    @Override
    public int getGroupCount() {
        return sellingItems.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return sellingItems.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return sellingItems.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sellingItems.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
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
        int stringId = (0 == i) ? R.string.selling_tab_active_listing : R.string.selling_tab_comparable_listing;
        buyingTagListsHeader.setText(stringId);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        if (0 == groupPosition) {
            view = inflater.inflate(R.layout.layout_selling_active_item, null);
            // Invoke references to UI components
            LinearLayout imagesContainer = view.findViewById(R.id.imagesContainer);
            TextView buyingItemPrice = view.findViewById(R.id.buyingItemPrice);
            TextView buyingItemFullAddress = view.findViewById(R.id.buyingItemFullAddress);
            TextView buyingItemFullDetails = view.findViewById(R.id.buyingItemFullDetails);
            ImageView buyingItemLikeImageView = view.findViewById(R.id.buyingItemLikeImageView);
            LinearLayout sellingActiveItemOffersContainerLayout = view.findViewById(R.id.sellingActiveItemOffersContainerLayout);

            // Init UI
            final SellingItem buyingItem = sellingItems.get(groupPosition).get(childPosition);
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
                    SellingListAdapter.this.notifyDataSetChanged();
                }
            });

            // TODO Fill the container with images
            // TODO Fill the container with offers

        } else {
            view = inflater.inflate(R.layout.layout_selling_comparable_item, null);
            // Invoke references to UI components
            LinearLayout imagesContainer = view.findViewById(R.id.imagesContainer);
            TextView buyingItemPrice = view.findViewById(R.id.buyingItemPrice);
            TextView buyingItemFullAddress = view.findViewById(R.id.buyingItemFullAddress);
            TextView buyingItemFullDetails = view.findViewById(R.id.buyingItemFullDetails);
            ImageView buyingItemLikeImageView = view.findViewById(R.id.buyingItemLikeImageView);

            // Init UI
            final SellingItem buyingItem = sellingItems.get(groupPosition).get(childPosition);
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
                    SellingListAdapter.this.notifyDataSetChanged();
                }
            });

            // TODO Fill the container with images
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
