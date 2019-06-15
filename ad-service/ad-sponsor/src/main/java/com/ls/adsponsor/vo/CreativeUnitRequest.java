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
public class CreativeUnitRequest {
    
    private List<CreativeUnitItem> unitItems;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreativeUnitItem{
        
        private Long creativeId;
        
        private Long unitId;
    }
}
