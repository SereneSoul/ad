package com.ls.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.service.ICreativeService;
import com.ls.adsponsor.vo.CreativeRequest;
import com.ls.adsponsor.vo.CreativeResponse;
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
public class CreativeOperateController {
    
    private final ICreativeService creativeService;

    @Autowired
    public CreativeOperateController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }
    
    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException {
        log.info("ad-sponsor: createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
