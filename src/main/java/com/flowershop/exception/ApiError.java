package com.flowershop.exception;

import java.time.Instant;

public class ApiError {

    private int status;
    private String message;
    private String path;
    private String error;
    private Instant timestamp;

    public ApiError(int status, String message, String path, String error) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.error = error;
        this.timestamp = Instant.now();
    }

    public  int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public String getPath() {
        return path;
    }
    public String getError() {
        return error;
    }
    public Instant getTimestamp() {
        return timestamp;
    }

}
