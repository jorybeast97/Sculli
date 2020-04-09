package com.magnoliaory.scullicommunicationserver.formula;

import java.util.List;

/**
 * 信息格式化接口,针对不同的协议重写方法
 */
public interface DataFormat<T> {

    //解析网关发送的协议数据
    public List<T> analyzeInformation(String msg);
}
