package com.ls.adsearch.index.creativeunit;

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
public class CreativeUnitObject {
    
    private Long adId;
    
    private Long unitId;
    
    //key adId-unitId
}
