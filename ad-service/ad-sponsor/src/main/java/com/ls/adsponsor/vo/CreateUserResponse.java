package com.ls.adsponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    
    private Long userId;
    
    private String username;
    
    private String token;
    
    private Date createTime;
    
    private Date updateTime;
}
