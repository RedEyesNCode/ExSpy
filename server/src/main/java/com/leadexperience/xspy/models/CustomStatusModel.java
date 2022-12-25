package com.leadexperience.xspy.models;

public class CustomStatusModel extends StatusCodeModel {

    public Object data;

    public CustomStatusModel(String status, int code, String message, Object data) {
        super(status, code, message);
        this.data = data;
    }

    public CustomStatusModel(String status, int code, Object data) {
        super(status, code);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CustomStatusModel(String status, int code, String message) {
        super(status, code, message);
    }

    public CustomStatusModel(String status, int code) {
        super(status, code);
    }
}
