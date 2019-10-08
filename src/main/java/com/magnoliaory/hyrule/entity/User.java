package com.magnoliaory.hyrule.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "username", nullable = false, length = 25)
    private String userName;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "true_name", nullable = false)
    private String trueName;

    @Column(name = "phone_number", nullable = true, length = 11)
    private String phoneNumber;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "delete_status", nullable = false)
    private boolean deleteStatus;

    @Column(name = "active_code", nullable = false)
    private boolean activeCode;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @ManyToMany(mappedBy = "users" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", activeCode=" + activeCode +
                ", createTime=" + createTime +
                '}';
    }
}
