package com.boot.smartrelay.mysql.repository;

import com.boot.smartrelay.mysql.entity.UserDevice;
import com.boot.smartrelay.mysql.repository.AdminDeviceJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 테스트 종료 후에 롤백이 자동적으로 진행된다.
 */
//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DataJpaTest // JPA 관련된 Component만 업로드함
public class AdminDeviceTest {

    @Autowired
    AdminDeviceJpaRepository adminDeviceJpaRepository;

    @Test
    @DisplayName("Save Device to user_device")
    public void save(){

        UserDevice userDevice = UserDevice.builder()
                .id(null)
                .userId("song")
                .deviecId("song-device")
                .build();

        UserDevice saveUserDevice = adminDeviceJpaRepository.save(userDevice);

        Assertions.assertEquals(userDevice , saveUserDevice);


    }
}
