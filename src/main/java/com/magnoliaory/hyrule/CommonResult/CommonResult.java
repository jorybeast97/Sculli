package com.magnoliaory.hyrule.CommonResult;

import lombok.Data;

@Data
public class CommonResult<T> {

    private String message;

    private Integer stateCode;

    private T data;

    protected CommonResult(String message, Integer stateCode, T data) {
        this.data = data;
        this.message = message;
        this.stateCode = stateCode;
    }

    /**
     * 操作成功
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(message, ResultCodeImp.SUCCESS.getStateCode(), data);
    }

    /**
     * 操作失败
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(T data, String message) {
        return new CommonResult<>(message, ResultCodeImp.FAILED.getStateCode(), data);
    }

    /**
     * 没有权限
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> noPermission(T data, String message) {
        return new CommonResult<>(message, ResultCodeImp.FORBIDDEN.getStateCode(), data);
    }

    /**
     * 未登录
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(T data, String message) {
        return new CommonResult<>(message, ResultCodeImp.UNAUTHORIZED.getStateCode(), data);
    }

}
