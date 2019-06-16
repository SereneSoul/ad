package com.ls.adsearch.index.interest;

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
public class UnitItObject {
    
    private Long unitId;
    
    private String itTag;
}
