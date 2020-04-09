package com.magnoliaory.scullientityoperation.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 监控点
 */
@Data
public class Monitor {

    @TableId(type = IdType.AUTO)
    private int id;

    /**
     * 监控点ID
     */
    private String monitorId;

    /**
     * 监控点名称
     */
    private String monitorName;

    /**
     * 监控点区域
     */
    private String regional;

    /**
     * 监控器Host
     */
    private String host;

    /**
     * 监控点端口
     */
    private String port;

    /**
     * 经度
     */
    private double longtitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 预警上限
     */
    private double alertUpperLimit;

    /**
     * 预警下限
     */
    private double alertLowerLimit;

    /**
     * 报警上限
     */
    private double ceiling;

    /**
     * 报警下限
     */
    private double lower;

    /**
     * 监控点当前连接状态
     */
    private boolean connectionStatus;

    /**
     * 删除状态
     */
    private boolean deleteStatus;

    /**
     * 上次更新时间
     */
    private Date updateTime;


}
