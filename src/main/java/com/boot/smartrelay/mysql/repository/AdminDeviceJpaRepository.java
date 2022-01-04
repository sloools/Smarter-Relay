package com.boot.smartrelay.mysql.repository;

import com.boot.smartrelay.mysql.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDeviceJpaRepository extends JpaRepository<UserDevice, Integer> {
}
