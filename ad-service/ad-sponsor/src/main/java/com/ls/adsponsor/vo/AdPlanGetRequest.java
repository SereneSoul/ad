package com.ls.adsponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {
    
    private List<Long> ids;
    
    private Long userId;
    
    public boolean validate(){
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
