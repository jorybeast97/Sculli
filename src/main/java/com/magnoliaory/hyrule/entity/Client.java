package com.magnoliaory.hyrule.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    /**
     * 客户端ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    /**
     * 客户端名称
     */
    @Column(name = "client_name", nullable = false)
    private String clientName;

    /**
     * 客户端物理地址
     */
    @Column(name = "address", nullable = true)
    private String address;

    /**
     * 客户端描述
     */
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * 客户端主机地址,该主机地址必须为唯一
     * HOST地址是客户端唯一的凭证
     */
    @Column(name = "host", nullable = false, unique = true)
    private String host;

    /**
     * 客户端端口
     */
    @Column(name = "port", nullable = false)
    private int port;

    /**
     * 客户端连接状态
     */
    @Column(name = "connection_status", nullable = false)
    private boolean connectionStatus;

    /**
     * 客户端激活状态,用于屏蔽某些客户端
     */
    @Column(name = "active_status", nullable = false)
    private boolean activeStatus;

}
