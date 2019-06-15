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
public class AdUnitItRequest {

    private List<UnitIt> unitIts;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitIt {
        
        private Long unitId;
        private String itTag;
    }
}
