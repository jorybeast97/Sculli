package com.magnoliaory.scullientityoperation.common;

import lombok.Data;

/**
 * 系统统一返回值,使用ResponeState作为Http状态码
 * @param <T>
 */
@Data
public class CommonResult <T> {

    private T data;


}
