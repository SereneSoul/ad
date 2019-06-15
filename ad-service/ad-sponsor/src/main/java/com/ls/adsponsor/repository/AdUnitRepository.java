package com.ls.adsponsor.repository;

import com.ls.adsponsor.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lijiayin
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    
    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);
    
    List<AdUnit> findAllByUnitStatus(Integer status);
}
