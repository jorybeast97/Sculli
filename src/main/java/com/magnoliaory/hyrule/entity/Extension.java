package com.magnoliaory.hyrule.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "extension")
public class Extension {

    /**
     * 分机主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extension_id")
    private int extensionId;

    /**
     * 分机名称
     */
    @Column(name = "extension_name",nullable = false)
    private String extensionName;

    /**
     * 分机地址码(安环码),在收发信息的时候用于辨认来自哪个安环之下的传感器.
     */
    @Column(name = "enxtension_code", nullable = false)
    private String entensionCode;

    /**
     * 信息描述
     */
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * 安环真实地址
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * 所属客户端id
     */
    @Column(name = "client_id", nullable = false)
    private int clientId;

    /**
     * 安环连接状态
     */
    @Column(name = "connection_status", nullable = false)
    private boolean connectionStatus;


}
