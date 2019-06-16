package com.ls.adsearch.client;

import com.ls.adcommon.vo.CommonResponse;
import com.ls.adsearch.client.vo.AdPlan;
import com.ls.adsearch.client.vo.AdPlanGetRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lijiayin
 */
@Component
public class SponsorClientHystrix implements SponsorClient {
    
    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-ad-sponsor-error");
    }
}
