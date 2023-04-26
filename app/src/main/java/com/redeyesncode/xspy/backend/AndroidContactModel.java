package com.redeyesncode.xspy.backend;

import android.os.Parcel;
import android.os.Parcelable;

public class AndroidContactModel implements Parcelable {
    protected AndroidContactModel(Parcel in) {
        ContactName = in.readString();
        ContactNumber = in.readString();
    }

    public static final Creator<AndroidContactModel> CREATOR = new Creator<AndroidContactModel>() {
        @Override
        public AndroidContactModel createFromParcel(Parcel in) {
            return new AndroidContactModel(in);
        }

        @Override
        public AndroidContactModel[] newArray(int size) {
            return new AndroidContactModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ContactName);
        parcel.writeString(ContactNumber);
    }

    private String ContactName;
    private String ContactNumber;

    public AndroidContactModel() {
    }

    public AndroidContactModel(String contactName, String contactNumber) {
        ContactName = contactName;
        ContactNumber = contactNumber;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}
