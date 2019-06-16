package com.ls.adsearch.client;

import com.ls.adcommon.vo.CommonResponse;
import com.ls.adsearch.client.vo.AdPlan;
import com.ls.adsearch.client.vo.AdPlanGetRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lijiayin
 */
@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class)
public interface SponsorClient {
    
    @PostMapping(value = "/ad-sponsor/get/adPlan")
    CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request);
}
