package com.ls.adsearch.index.keyword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitKeywordObject {
    
    private Long unitId;
    
    private String keyword;
}
