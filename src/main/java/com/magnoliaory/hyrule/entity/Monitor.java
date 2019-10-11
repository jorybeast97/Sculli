package com.magnoliaory.hyrule.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "monitor")
public class Monitor {

    /**
     * 监测点Id
     */
    @Id
    @GeneratedValue
    @Column(name = "monitor_id")
    private int monitorId;

    /**
     * 信号名称,例如"xxx检测点温度"
     */
    @Column(name = "signal_name", nullable = false)
    private String signalName;

    /**
     * 设备名称
     */
    @Column(name = "equipment_name", nullable = false)
    private String equipmentName;

    /**
     * 设备主机地址
     */
    @Column(name = "host", nullable = false)
    private String host;

    /**
     * 设备端口
     */
    @Column(name = "port", nullable = false)
    private int port;

    /**
     * 功能码
     */
    @Column(name = "function_code", nullable = false)
    private String functionCode;

    /**
     * 分机id
     */
    @Column(name = "extension_id", nullable = false)
    private int extensionId;

    /**
     * 寄存器地址
     */
    @Column(name = "register_address", nullable = false)
    private String registerAddress;

    /**
     * 单位
     */
    @Column(name = "unit", nullable = false)
    private String unit;

    /**
     * 监测点安装地址
     */
    @Column(name = "address", nullable = true)
    private String address;

    /**
     * 监测点状态
     */
    @Column(name = "monitor_status", nullable = false)
    private boolean monitorStatus;

    /**
     * 上限值
     */
    @Column(name = "upper_limit", nullable = true)
    private double upperLimit;

    /**
     * 下限值
     */
    @Column(name = "lower_limit", nullable = true)
    private double lowerLimit;

    /**
     * 预警上限
     */
    @Column(name = "upper_limit_early_warning")
    private  double upperLimitEarlyWarning;

    /**
     * 预警下限
     */
    @Column(name = "lower_limit_early_warning")
    private double lowerLimitEarlyWarning;
}
