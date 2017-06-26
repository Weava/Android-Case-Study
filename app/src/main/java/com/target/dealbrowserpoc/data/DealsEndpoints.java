package com.target.dealbrowserpoc.data;

import com.target.dealbrowserpoc.data.models.DealItemsResponse;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;

/**
 * Retrofit endpoints relating to our Deals API
 */
public interface DealsEndpoints {

    /**
     * Gets a list of {@link com.target.dealbrowserpoc.data.models.DealItem} using
     * {@link DealItemsResponse} as a wrapper class.
     *
     * @return
     *      {@link Result<DealItemsResponse>} list of deals items in payload
     */
    @GET("deals")
    Observable<Result<DealItemsResponse>> getDeals();
}
