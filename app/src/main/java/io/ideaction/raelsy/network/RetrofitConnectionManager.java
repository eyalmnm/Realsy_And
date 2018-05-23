package io.ideaction.raelsy.network;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.ideaction.raelsy.BuildConfig;
import io.ideaction.raelsy.network.models.BuyingItemModel;
import io.ideaction.raelsy.network.models.BuyingItemsResponse;
import io.ideaction.raelsy.network.rest.GetBuyingItemsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnectionManager {
    private static final String TAG = "RetrofitConnectionMngr";

    private Retrofit retrofit;
    private String baseUrl;

    private Context context;

    private static RetrofitConnectionManager instance = null;

    public static RetrofitConnectionManager getInstance(Context context) {
        if (null == instance) {
            instance = new RetrofitConnectionManager(context);
        }
        return instance;
    }

    private RetrofitConnectionManager(Context context) {
        this.context = context;
        baseUrl = BuildConfig.SERVER_BASE_URL;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

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
