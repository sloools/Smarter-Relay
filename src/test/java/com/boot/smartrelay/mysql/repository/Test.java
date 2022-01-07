package com.boot.smartrelay.mysql.repository;

import com.boot.smartrelay.mysql.entity.UserDevice;
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

        UserDevice userDevice = UserDevice.builder()
                .id(null)
                .userId("song")
                .deviecId("song-device2")
                .build();

        UserDevice saveUserDevice = adminDeviceJpaRepository.save(userDevice);

        Assertions.assertEquals(userDevice, saveUserDevice);


    }
}
