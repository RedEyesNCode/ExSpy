package com.leadexperience.xspy.models;

public class StatusCodeModel {
    private String status;
    private int code;
    private String message;

    public StatusCodeModel(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusCodeModel(String status, int code) {
        this.status = status;
        this.code = code;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
