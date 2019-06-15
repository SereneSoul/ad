package com.ls.adsponsor.repository;

import com.ls.adsponsor.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lijiayin
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * 根据用户名查找用户记录
     * @param username
     * @return
     */
    AdUser findByUsername(String username);
}
