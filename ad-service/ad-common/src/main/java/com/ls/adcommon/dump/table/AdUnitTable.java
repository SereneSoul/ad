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
public class AdUnitTable {
    
    private Long unitId;
    
    private Integer unitStatus;
    
    private Integer positionType;
    
    private Long planId;
}
