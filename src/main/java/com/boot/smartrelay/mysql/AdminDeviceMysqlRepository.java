package com.boot.smartrelay.mysql;

import com.boot.smartrelay.Utils;
import com.boot.smartrelay.beans.ResponseBox;
import com.boot.smartrelay.beans.User;
import com.boot.smartrelay.mysql.entity.UserDevice;
import com.boot.smartrelay.mysql.repository.AdminDeviceJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor

public class AdminDeviceMysqlRepository {

    private final AdminDeviceJpaRepository adminDeviceJpaRepository;

    /* Delete는 @Transactional 붙여줘야 함. service 개발되면 여기 붙은 어노테이션 제거해보기 */
    @Transactional
    public ResponseBox deleteAdminUserDeviceIdLists(String userId, List<String> deviceList) {
        boolean deleteFlg = false;
        boolean status = true;
        String message = "성공적으로 수정되었습니다.";

        try {
            boolean checkUserId = adminDeviceJpaRepository.existsByUserId(userId);
            if(!checkUserId){
                status = false;
                message = "해당 유저가 존재하지 않습니다. 유저 생성 먼저 해주세요.";
            }else{
                for (String deviceId : deviceList){
                    if(adminDeviceJpaRepository.deleteUserDeviceByUserIdAndDeviecId(userId, deviceId) > 0){
                        deleteFlg = true;
                    }else{
                        continue;
                    }
                }
            }
            if(!deleteFlg){
                status = false;
                message = "해당 유저에게 할당 된 디바이스가 아니여서 삭제할 수 없습니다.";
            }
        }catch (Exception e){
            e.printStackTrace();
            status = false;
            message = "수정에 실패하였습니다. DB 커넥션을 확인해주시기 바랍니다.";
            log.error("Mysql - ModifyAdminUserDeviceIdLists error 입니다. userId : {}", userId);
        }

        return Utils.makeResponseBox(status, message);
    }

    public List<String> getValidDeviceIdLists(User user) {
        String userId = user.getId();
        List<UserDevice> userDeviceList = null;
        List<String> deviceIds = null;
        try {
            userDeviceList = adminDeviceJpaRepository.findAllDeviceIdByUserId(userId);
            deviceIds = userDeviceList
                                .stream()
                                .map(UserDevice::getDeviecId)
                                .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            log.error("유효하고 추가가능한 디바이스를 리턴하는데 실패 에러 user : {}", user.getId());
        }

        return deviceIds;

    }

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
