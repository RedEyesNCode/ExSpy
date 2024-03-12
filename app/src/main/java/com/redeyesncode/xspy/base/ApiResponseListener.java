package com.redeyesncode.xspy.base;

import com.google.gson.JsonElement;

public interface ApiResponseListener {

    void onSuccess(JsonElement jsonElement);
    void onError(String error);
    


}
