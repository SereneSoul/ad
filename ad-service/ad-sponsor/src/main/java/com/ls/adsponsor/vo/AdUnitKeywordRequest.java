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
public class AdUnitKeywordRequest {

    private List<UnitKeyword> unitKeywords;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitKeyword {
        
        private Long unitId;
        private String keyword;
    }
}
