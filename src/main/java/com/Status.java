package com;

/**
 * Created by George.Mao on 11/14/2014.
 */
public enum Status {
    // MongoDB work around: We have to use values that are strings and match the ENUM value because of MongoDB
    ACTIVE("ACTIVE"), SOLD ("SOLD"), PROCESSED ("PROCESSED");

    private String statusCode;

    private Status(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
