package com.sileyouhe.mall.common.api;

public enum ResultCode implements IErrorCode {

    SUCCESS(200,"the request has succeeded"),
    FAILED(500, "Internal Server Error"),
    VALIDATE_FAILED(404,"Cannot find the requested resource"),
    UNAUTHORIZED(401,"Invalid login or token expires"),
    FORBIDDEN(403, "do not have permission to access");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
