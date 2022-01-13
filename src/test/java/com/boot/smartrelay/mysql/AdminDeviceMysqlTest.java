package com.boot.smartrelay.mysql;

import com.boot.smartrelay.beans.ResponseBox;
import com.boot.smartrelay.beans.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class AdminDeviceMysqlTest {

    @Autowired
    AdminDeviceMysqlRepository adminDeviceMysqlRepository;

//    @BeforeAll
//    public void before(){
//        UserDevice userDevice = UserDevice.builder()
//                .id(null)
//                .userId("song")
//                .deviecId("song-device2")
//                .build();
//    }


    @Test
    @DisplayName("Delete User Device ids Test")
    public void modifyUserDeviceIds(){
        String userId = "Andrew";
        List<String> deviceList = new ArrayList<>();
        deviceList.add("Andrew-device3");


        ResponseBox responseBox = adminDeviceMysqlRepository.deleteAdminUserDeviceIdLists(userId, deviceList);

        assertThat(responseBox.isStatus(), is(false));
    }

    @Test
    @DisplayName("Select Device Ids List by User Id Test")
    public void GetdeviceIdsByUserId(){
        User user = User.builder()
                .id("Song")
                .build();

        List<String> deviceIds = adminDeviceMysqlRepository.getValidDeviceIdLists(user);

        assertThat(deviceIds, hasItem("song-device")); // Check If List Has This Value

//        assertThat(deviceIds, hasSize(5)); // Check List Size
//
//        assertThat(deviceIds, containsInAnyOrder("song-device", "song-device2")); // Check List Contaions Items
//
//        assertThat(deviceIds, not(IsEmptyCollection.empty())); // Check If It is Empty (Null or Collections.emptyList() 둘 다 받음)

    }


    @Test
    @DisplayName("Check If Device Id Is Exist Test when it's false")
    public void IsExistDeviceIdTest(){
        List<String> deviceIds = new ArrayList<>();
        deviceIds.add("andrew-device000");
        deviceIds.add("andrew-device001");
        deviceIds.add("andrew-device002");
        deviceIds.add("andrew-device003");

        boolean result = adminDeviceMysqlRepository.isExistDeviceIds(deviceIds);

        assertThat(result, is(false));
    }

    @Test
    @DisplayName("Check If Device Id Is Exist Test when it's true")
    public void IsExistDeviceIdTestWhenExistTrue(){
        List<String> deviceIds = new ArrayList<>();
        deviceIds.add("andrew-device1"); // exist device id
        deviceIds.add("andrew-device22");
        deviceIds.add("andrew-device33");
        deviceIds.add("andrew-device44");

        boolean result = adminDeviceMysqlRepository.isExistDeviceIds(deviceIds);

        assertThat(result, is(true));
    }


    @Test
    @DisplayName("Save to user_device Test")
    public void saveUserAndDeviceInfoTest(){

        String userID = "Andrew";

        List<String> deviceIds = new ArrayList<>();
        deviceIds.add("andrew-device1");
        deviceIds.add("andrew-device2");
        deviceIds.add("andrew-device3");

        adminDeviceMysqlRepository.saveDeviceIds(userID, deviceIds);
    }
}

