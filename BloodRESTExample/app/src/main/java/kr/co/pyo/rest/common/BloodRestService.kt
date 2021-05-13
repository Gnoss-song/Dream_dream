package kr.co.pyo.rest.common

import kr.co.pyo.rest.data.BloodModel
import kr.co.pyo.rest.data.OkFailResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BloodRestService {
    @FormUrlEncoded
    @POST("/androidNetwork/blood")
    fun insertBlood(
        @Field("patientName") patientName: String?,
        @Field("bloodType") bloodType: String?,
        @Field("statusText") statusText: String?,
        @Field("donationType") donationType: String?,
        @Field("bloodValue") bloodValue: String?,
        @Field("hospital") hospital: String?,
        @Field("hospitalPhone") hospitalPhone: String?,
        @Field("relationText") relationText: String?,
        @Field("careName") careName: String?,
        @Field("carePhone") carePhone: String?
    ): Call<OkFailResult>

    @GET("/androidNetwork/blood/infos")
    fun requestBloodSelect(): Call<BloodModel>

    @GET("/androidNetwork/images")
    fun selectGirlImages(): Call<ResponseBody>
}