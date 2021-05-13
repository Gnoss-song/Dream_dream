package com.kb.openapi.beach_traffic;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataOpenTrafficAPIService {
    @GET("B552584/LezShotSvc/getLezShotInfo")
    Call<ResponseBody>   trafficOpenAPIService(@Query("ServiceKey") String serviceKey);

    @GET("seantour_map/travel/getBeachCongestionApi.do")
    Call<ResponseBody>   beachCongestion();
}
