package io.ideaction.raelsy.network.rest;

import io.ideaction.raelsy.network.models.SellingItemsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetSellingItemsInterface {

    @GET("properties/active_listing_items")
    Call<SellingItemsResponse> getActiveListingItems(@Query("my_id") String apiKey);

    @GET("properties/comparable_items")
    Call<SellingItemsResponse> getComparableItems(@Query("my_id") String apiKey);

}
