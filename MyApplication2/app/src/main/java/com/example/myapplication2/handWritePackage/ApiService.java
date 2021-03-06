package com.example.myapplication2.handWritePackage;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Akshay Raj on 05/02/18.
 * akshay@snowcorp.org
 * www.snowcorp.org
 */

public interface ApiService {
    String BASE_URL = "https://10836008.000webhostapp.com";

    @Multipart
    @POST("upload-new.php")
    // @POST("test.php")
    Call<ResponseBody> uploadMultiple(
            @Part("description") RequestBody description,
            @Part("size") RequestBody size,
            @Part("diaryNo") RequestBody diaryNoToserver,
            @Part("picTarget") RequestBody picTarget,
            @Part List<MultipartBody.Part> files);
}
