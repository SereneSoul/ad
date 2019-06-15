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
public class AdPlanRequest {
    
    private Long id;
    
    private Long userId;
    
    private String planName;
    
    private String startDate;
    
    private String endDate;
    
    public boolean createValidate(){
        return userId != null
                && StringUtils.isNotBlank(planName)
                && StringUtils.isNotBlank(startDate)
                && StringUtils.isNotBlank(endDate);
    }

    public boolean updateValidate(){
        return id != null && userId != null;
    }

    public boolean deleteValidate(){
        return id != null && userId != null;
    }
}
