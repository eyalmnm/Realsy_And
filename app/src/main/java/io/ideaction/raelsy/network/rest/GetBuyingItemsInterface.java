package io.ideaction.raelsy.network.rest;

import io.ideaction.raelsy.network.models.BuyingItemsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetBuyingItemsInterface {

    @GET("properties/shared_with_me_items")
    Call<BuyingItemsResponse> getSharedWithMeItems(@Query("my_id") String apiKey);

    @GET("properties/my_favorite_items")
    Call<BuyingItemsResponse> getMyFavoriteItems(@Query("my_id") String apiKey);
}
