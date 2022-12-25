package com.redeyesncode.xspy.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

public class FileBody implements Parcelable {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileBody(File file) {
        this.file = file;
    }

    public FileBody() {
    }

    protected FileBody(Parcel in) {
    }

    public static final Creator<FileBody> CREATOR = new Creator<FileBody>() {
        @Override
        public FileBody createFromParcel(Parcel in) {
            return new FileBody(in);
        }

        @Override
        public FileBody[] newArray(int size) {
            return new FileBody[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
