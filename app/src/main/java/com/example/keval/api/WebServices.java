package com.example.keval.api;

import com.example.keval.models.HotelResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WebServices {
    @GET("hotels/list/")
    Call<HotelResponse> getHotelList(@QueryMap Map<String, String> queryParam);
}
