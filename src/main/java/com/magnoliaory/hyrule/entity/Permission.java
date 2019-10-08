package com.magnoliaory.hyrule.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int permissionId;

    @Column(name = "permission_name", nullable = false)
    private String permissionName;

    @Column(name = "permission_lable", nullable = false)
    private String permissionLable;

    @ManyToMany(mappedBy = "permissions",cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", permissionLable='" + permissionLable + '\'' +
                '}';
    }
}
