package com.magnoliaory.hyrule.CommonResult;

/**
 * 操作失败
 */
public enum ResultCodeImp implements ResultCode {

    /**
     * 定义不同类型的状态码
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private Integer stateCode;

    private String message;

    ResultCodeImp(Integer stateCode, String message) {
        this.message = message;
        this.stateCode = stateCode;
    }
    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Integer getStateCode() {
        return null;
    }
}
