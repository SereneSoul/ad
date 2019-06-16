package com.ls.adsearch.index.district;

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
public class UnitDistrictObject {
    
    private Long unitId;
    
    private String province;
    
    private String city;
    
    //key province-city
}
