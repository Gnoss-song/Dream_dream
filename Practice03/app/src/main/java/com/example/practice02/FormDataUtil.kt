package com.example.practice02

import android.util.Log
import okhttp3.*
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-05-13
 * @desc
 */
object FormDataUtil {
    fun getBody(key: String, value: Any): MultipartBody.Part {
        return MultipartBody.Part.createFormData(key, value.toString())
    }

    //key : 파일 파라미터명
    fun getImageBody(key: String, file: File): MultipartBody.Part {
        val surveyBody = RequestBody.create(MediaType.parse("image/*"), file)
        return MultipartBody.Part.createFormData(
                key,
                file.name,
                surveyBody
        )
    }

//    private fun fileUploadAsync(queryString: String, fileLocation:String) {
//        Thread {
//            val uploadFile = File(fileLocation) //파일(사진)이 저장된 위치
//            var response: Response? = null
//            try {
//                //업로드는 타임 및 리드타임을 넉넉히 준다.
//                val toServer: OkHttpClient = OkHttpClient.Builder()
//                        .connectTimeout(20, TimeUnit.SECONDS)
//                        .readTimeout(20, TimeUnit.SECONDS)
//                        .writeTimeout(20, TimeUnit.SECONDS)
//                        .build()
//
//                val fileUploadBody: RequestBody = MultipartBody.Builder()
//                        .setType(MultipartBody.FORM) //파일 업로드시 반드시 설정
//                        .addFormDataPart("queryName1", queryString) //기본 쿼리
//                        .addFormDataPart(
//                                "uploadfile", uploadFile.name,
//                                RequestBody.create(MediaType.parse("image/*"), uploadFile)
//                        )
//                        .build()
//                //요청 세팅
//                val request: Request = Request.Builder()
//                        .url("http://172.30.1.27:5678/androidNetwork/fileUpload.pyo")
//                        .post(fileUploadBody) //반드시 post로
//                        .build()
//                //동기 방식
//                response = toServer.newCall(request).execute()
//                if (response.isSuccessful) {
//                    Log.e("TAG", "OK")
//                }
//            } catch (e: Exception) {
//                Log.e("UploadError", e.toString())
//            } finally {
//                response?.close()
//            }
//        }.start()
//    }


}