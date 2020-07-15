package com.cjh.community2.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }
}
