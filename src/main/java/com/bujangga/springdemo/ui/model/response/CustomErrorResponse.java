package com.bujangga.springdemo.ui.model.response;

import java.time.LocalDateTime;
import java.util.Date;

public class CustomErrorResponse {
    private LocalDateTime timeStamp;
    private String message;
    private int status;
    private String path;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(LocalDateTime timeStamp, String message, int status, String path) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
