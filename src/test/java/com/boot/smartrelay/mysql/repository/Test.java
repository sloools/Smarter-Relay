package com.boot.smartrelay.mysql.repository;

import com.boot.smartrelay.mysql.entity.AdminDeviceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {

    @Autowired
    AdminDeviceJpaRepository adminDeviceJpaRepository;

    @org.junit.jupiter.api.Test
    @DisplayName("Save Device to user_device")
    public void save() {

        AdminDeviceEntity userDevice = AdminDeviceEntity.builder()
                .id(null)
                .userId("song")
                .deviecId("song-device2")
                .build();

        AdminDeviceEntity saveUserDevice = adminDeviceJpaRepository.save(userDevice);

        Assertions.assertEquals(userDevice, saveUserDevice);


    }
}
