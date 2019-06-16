package com.ls.adsearch.client.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdPlan {
    
    private Long id;

    private Long userId;

    private String planName;

    private Integer planStatus;

    private Date startDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;
}
