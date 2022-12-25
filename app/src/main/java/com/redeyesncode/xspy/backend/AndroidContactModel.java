package com.redeyesncode.xspy.backend;

public class AndroidContactModel {

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
