package com.ls.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.entity.AdPlan;
import com.ls.adsponsor.service.IAdPlanService;
import com.ls.adsponsor.vo.AdPlanGetRequest;
import com.ls.adsponsor.vo.AdPlanRequest;
import com.ls.adsponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lijiayin
 */
@Slf4j
@RestController
public class AdPlanOperateController {
    
    private final IAdPlanService adPlanService;

    @Autowired
    public AdPlanOperateController(IAdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }
    
    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: createAdPlan -> {}", JSON.toJSONString(request));      
        return adPlanService.createAdPlan(request);
    }
    
    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException {
        log.info("ad-sponsor: getAdPlanByIds -> {}", JSON.toJSONString(request));
        return adPlanService.getAdPlanByIds(request);
    }
    
    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: updateAdPlan -> {}", JSON.toJSONString(request));
        return adPlanService.updateAdPlan(request);
    }
    
    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: deleteAdPlan -> {}", JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }
}
