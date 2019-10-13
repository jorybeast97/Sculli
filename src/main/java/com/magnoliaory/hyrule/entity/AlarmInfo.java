package com.magnoliaory.hyrule.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "alarm_info")
public class AlarmInfo {

    @Id
    @GeneratedValue
    @Column(name = "alarm_info_id")
    private long alarmInfoId;

    @Column(name = "alarm_signal", nullable = false)
    private String alarmSignal;

    @Column(name = "alarm_value", nullable = false)
    private String alarmValue;

    @Column(name = "alarm_date", nullable = false)
    private Date alarmDate;

    @Column(name = "solve_status", nullable = false)
    private boolean solveStatus;


}
