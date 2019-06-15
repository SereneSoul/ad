package com.ls.adsponsor.service;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.entity.AdPlan;
import com.ls.adsponsor.vo.AdPlanGetRequest;
import com.ls.adsponsor.vo.AdPlanRequest;
import com.ls.adsponsor.vo.AdPlanResponse;

import java.util.List;

/**
 * @author lijiayin
 */
public interface IAdPlanService {

    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广计划
     * @param request
     * @return
     * @throws AdException
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     * @param request
     * @throws AdException
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
