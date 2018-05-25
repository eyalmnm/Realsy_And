package io.ideaction.raelsy.network;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.ideaction.raelsy.BuildConfig;
import io.ideaction.raelsy.network.models.BuyingItemModel;
import io.ideaction.raelsy.network.models.BuyingItemsResponse;
import io.ideaction.raelsy.network.models.SellingItemsResponse;
import io.ideaction.raelsy.network.rest.GetBuyingItemsInterface;
import io.ideaction.raelsy.network.rest.GetSellingItemsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnectionManager {
    private static final String TAG = "RetrofitConnectionMngr";

    private Retrofit retrofit;
    private String baseUrl;

    private static RetrofitConnectionManager instance = null;

    public static RetrofitConnectionManager getInstance() {
        if (null == instance) {
            instance = new RetrofitConnectionManager();
        }
        return instance;
    }

    private RetrofitConnectionManager() {
        baseUrl = BuildConfig.SERVER_BASE_URL;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Buying API Methods
    public void getMyFavoriteItems(String myId, Callback<BuyingItemsResponse> callback) {
        GetBuyingItemsInterface apiService = retrofit.create(GetBuyingItemsInterface.class);
        Call<BuyingItemsResponse> call = apiService.getMyFavoriteItems(myId);
        call.enqueue(callback);
    }

    public void getSharedWithMeBuyingItems(String myId, Callback<BuyingItemsResponse> callback) {
        GetBuyingItemsInterface apiService = retrofit.create(GetBuyingItemsInterface.class);
        Call<BuyingItemsResponse> call = apiService.getSharedWithMeItems(myId);
        call.enqueue(callback);
    }

    // Selling API Methods
    public void getActiveListingItems(String myId, Callback<SellingItemsResponse> callback) {
        GetSellingItemsInterface apiService = retrofit.create(GetSellingItemsInterface.class);
        Call<SellingItemsResponse> call = apiService.getActiveListingItems(myId);
        call.enqueue(callback);
    }

    public void getComparableItems(String myId, Callback<SellingItemsResponse> callback) {
        GetSellingItemsInterface apiService = retrofit.create(GetSellingItemsInterface.class);
        Call<SellingItemsResponse> call = apiService.getComparableItems(myId);
        call.enqueue(callback);
    }
}

//    public void getSharedWithMeBuyingItems(String myId, Callback<BuyingItemsResponse> callback) {
//        GetBuyingItemsInterface apiService = retrofit.create(GetBuyingItemsInterface.class);
//        Call<BuyingItemsResponse> call = apiService.getSharedWithMeItems(myId);
//        call.enqueue(new Callback<BuyingItemsResponse>() {
//            @Override
//            public void onResponse(Call<BuyingItemsResponse> call, Response<BuyingItemsResponse> response) {
//                List<BuyingItemModel> items = response.body().getResults();
//                Log.d(TAG, "Found items: " + items.size());
//            }
//
//            @Override
//            public void onFailure(Call<BuyingItemsResponse> call, Throwable t) {
//                Log.e(TAG, "getSharedWithMeBuyingItems -> onFailure", t);
//            }
//        });
//    }
//}
