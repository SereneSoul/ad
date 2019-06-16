package com.ls.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.service.IAdUnitService;
import com.ls.adsponsor.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijiayin
 */
@Slf4j
@RestController
public class AdUnitOperateController {
    
    private final IAdUnitService adUnitService;

    @Autowired
    public AdUnitOperateController(IAdUnitService adUnitService) {
        this.adUnitService = adUnitService;
    }
    
    @PostMapping("/create/adUnit")
    public AdUnitResponse createAdUnit(@RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor: createAdUnit -> {}", JSON.toJSONString(request));
        return adUnitService.createUnit(request);
    }

    @PostMapping("/create/adUnitKeyword")
    public AdUnitKeywordResponse createAdUnitKeyword(@RequestBody AdUnitKeywordRequest request) throws AdException {
        log.info("ad-sponsor: createAdUnitKeyword -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitKeyword(request);
    }

    @PostMapping("/create/adUnitIt")
    public AdUnitItResponse createAdUnitIt(@RequestBody AdUnitItRequest request) throws AdException {
        log.info("ad-sponsor: createAdUnitIt -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitIt(request);
    }

    @PostMapping("/create/adUnitDistrict")
    public AdUnitDistrictResponse createAdUnitDistrict(@RequestBody AdUnitDistrictRequest request) throws AdException {
        log.info("ad-sponsor: createAdUnitDistrict -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitDistrict(request);
    }

    @PostMapping("/create/creativeUnit")
    public CreativeUnitResponse createCreativeUnit(@RequestBody CreativeUnitRequest request) throws AdException {
        log.info("ad-sponsor: createCreativeUnit -> {}", JSON.toJSONString(request));
        return adUnitService.createCreativeUnit(request);
    }
}
