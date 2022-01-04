package com.boot.smartrelay.mysql;

import com.boot.smartrelay.Utils;
import com.boot.smartrelay.beans.ResponseBox;
import com.boot.smartrelay.mysql.entity.UserDevice;
import com.boot.smartrelay.mysql.repository.AdminDeviceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDeviceMysqlRepository {

    private final AdminDeviceJpaRepository adminDeviceJpaRepository;

    public ResponseBox saveDeviceIds(List<String> deviceIds){
        boolean status = true;
        String message = "";

        try{
            deviceIds.stream().forEach(deviceId -> {
                                            UserDevice userDevice = UserDevice.builder()
                                                    .deviecId(deviceId)
                                                    .build();
                                            adminDeviceJpaRepository.save(userDevice);
                                        });
        }catch (Exception e){
            status = false;
            message = "server mysql error : mysql 디비에 디바이스 정보를 저장하는 데 실패했습니다.";
        }
        return Utils.makeResponseBox(status, message);
    }
}
