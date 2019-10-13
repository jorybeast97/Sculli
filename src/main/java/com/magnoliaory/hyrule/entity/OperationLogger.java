package com.magnoliaory.hyrule.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "operation_logger")
public class OperationLogger {

    /**
     * 操作日志主键
     */
    @Id
    @GeneratedValue
    @Column(name = "operation_logger_id")
    private long operationLoggerId;

    /**
     * 操作用户地址
     */
    @Column(name = "user_host", nullable = false)
    private String userHost;

    /**
     * 操作用户真实姓名
     */
    @Column(name = "user_true_name")
    private String userTrueName;

    /**
     * 操作描述
     */
    @Column(name = "operationDescription")
    private String operationDescription;

    /**
     * 操作时间
     */
    @Column(name = "operation_date", nullable = false)
    private Date operationDate;


}
