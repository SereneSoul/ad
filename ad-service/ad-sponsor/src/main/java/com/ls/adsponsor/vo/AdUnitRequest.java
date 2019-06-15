package com.ls.adsponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {
    
    private Long planId;
    
    private String unitName;
    
    private Integer positionType;
    
    private Long budget;
    
    public boolean createValidate(){
        return planId != null
                && StringUtils.isNotBlank(unitName)
                && positionType != null
                && budget != null;
    }
}
