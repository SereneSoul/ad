package com.ls.adsponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistricts;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitDistrict {
        
        private Long unitId;
        private String province;
        private String city;
    }
}
