package com.magnoliaory.scullientityoperation.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Extension implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;

    private String extensionId;

    private String extensionName;

    private String extensionHost;

    private int extensionPort;

    private boolean extensionConnectionStatus;

    private boolean extensionDeleteStatus;

    private Date connectionOnline;

    private Date connectionOffline;


}
