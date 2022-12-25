package com.redeyesncode.xspy.backend.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

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

        return START_STICKY;
    }
}
