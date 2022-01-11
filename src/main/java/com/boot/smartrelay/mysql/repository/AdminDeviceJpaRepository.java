package com.boot.smartrelay.mysql.repository;

import com.boot.smartrelay.beans.User;
import com.boot.smartrelay.mysql.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDeviceJpaRepository extends JpaRepository<UserDevice, Integer> {
    boolean existsByUserId(String userId);

    public boolean existsByDeviecId(String deviceId);

    public List<UserDevice> findAllDeviceIdByUserId(String userId);

//    boolean deleteyUserIdAndDeviecId(String userId, String deviceId);

    int deleteUserDeviceByUserIdAndDeviecId(String userId, String deviceId);
}
