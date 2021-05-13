package com.kb.openapi.activity;

import com.kb.openapi.pojo.StoreSaleResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DATAOpenAPIService {

    @GET("corona19-masks/v1/storesByAddr/json?")
    Call<StoreSaleResult> maskStoreByAddressInfo(@Query("address") String address);
}
