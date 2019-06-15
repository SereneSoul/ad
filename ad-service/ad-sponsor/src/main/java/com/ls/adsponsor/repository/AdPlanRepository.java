package com.ls.adsponsor.repository;

import com.ls.adsponsor.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lijiayin
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    /**
     * 根据id和userId查询计划
     * @param id
     * @param userId
     * @return
     */
    AdPlan findByIdAndUserId(Long id, Long userId);
    
    
    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);
    
    AdPlan findByUserIdAndPlanName(Long userId, String planName);
    
    List<AdPlan> findAllByPlanStatus(Integer status);
}
