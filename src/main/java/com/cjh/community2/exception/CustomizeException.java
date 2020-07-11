package com.cjh.community2.exception;

public class CustomizeException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(String message) {
        this.message = message;
    }
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }
}
