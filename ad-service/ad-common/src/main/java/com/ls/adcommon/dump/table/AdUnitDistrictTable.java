package com.ls.adcommon.dump.table;

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
public class AdUnitDistrictTable {
    
    private Long unitId;
    
    private String province;
    
    private String city;
}
