package com.ls.adsearch.controller;

import com.alibaba.fastjson.JSON;
import com.ls.adcommon.annotation.IgnoreResponseAdvice;
import com.ls.adcommon.vo.CommonResponse;
import com.ls.adsearch.client.SponsorClient;
import com.ls.adsearch.client.vo.AdPlan;
import com.ls.adsearch.client.vo.AdPlanGetRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lijiayin
 */
@Slf4j
@RestController
public class SearchController {
    
    private final SponsorClient sponsorClient;

    @Autowired
    public SearchController(@Qualifier("eureka-client-ad-sponsor") SponsorClient sponsorClient) {
        this.sponsorClient = sponsorClient;
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlans -> {}", JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }
}
