package com.workintech.spring17challenge.exceptions;

public class ApiResponseError {
    private String message;
    private String details;

    public ApiResponseError(String message, String details) {
        this.message = message;
        this.details = details;
    }

    // Getter and Setter methods

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
