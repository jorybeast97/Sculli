package com.magnoliaory.scullientityoperation.common;


public enum ResponeState {

    SUCCESS("200","操作成功"),
    NO_PERMISSION("403", "用户权限不足"),
    NOT_FOUND("404", "资源不存在"),
    SERVER_ERROR("500","服务器异常")
    ;

    private String stateCode;
    private String message;

    ResponeState(String stateCode, String message) {
        this.stateCode = stateCode;
        this.message = message;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
