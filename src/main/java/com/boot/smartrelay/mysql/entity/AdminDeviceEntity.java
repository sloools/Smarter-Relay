package com.boot.smartrelay.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="admin_device")
public class AdminDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "USER_ID")
    String userId;

    @Column(name = "DEVICE_ID", nullable = false)
    String deviecId;
}
