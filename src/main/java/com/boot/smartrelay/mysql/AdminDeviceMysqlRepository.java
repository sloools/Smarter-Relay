package com.boot.smartrelay.mysql;

import com.boot.smartrelay.Utils;
import com.boot.smartrelay.beans.ResponseBox;
import com.boot.smartrelay.mysql.entity.UserDevice;
import com.boot.smartrelay.mysql.repository.AdminDeviceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
@RequiredArgsConstructor
public class AdminDeviceMysqlRepository {

    private final AdminDeviceJpaRepository adminDeviceJpaRepository;


    public boolean isExistDeviceIds(List<String> deviceIds) {

        boolean result = false;

        for(String deviceId : deviceIds) {
            if(adminDeviceJpaRepository.existsByDeviecId(deviceId)){
                result = true;
                return result;
            }
        }
        // stream을 사용하면 forEach 중간에 break를 할 수 없다. 따라서 이런 케이스는 일반적인 for문을 쓰는 게 나음
        return result;
    }


    /**
     * AdminDeviceRepository.saveDeviceIds
     * 유저 아이디와 디바이스 아이디를 user_device 테이블에 저장
     * @param userId
     * @param deviceIds
     * @return
     */
    public ResponseBox saveDeviceIds(String userId, List<String> deviceIds){
        boolean status = true;
        String message = "";

        try{
            deviceIds.stream().forEach(deviceId -> {
                                            UserDevice userDevice = UserDevice.builder()
                                                    .userId(userId)
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
