package com.ls.adsponsor.vo;

import com.ls.adsponsor.constant.CommonStatus;
import com.ls.adsponsor.entity.Creative;
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
public class CreativeRequest {
    
    private String name;
    
    private Integer type;

    private Integer materialType;

    private Integer height;

    private Integer width;
    
    private Long size;
    
    private Integer duration;
    
    private Long userId;
    
    private String url;
    
    public Creative convertToEntity(){
        return Creative.builder()
                .name(name)
                .type(type)
                .materialType(materialType)
                .height(height)
                .width(width)
                .size(size)
                .duration(duration)
                .auditStatus(CommonStatus.VALID.getStatus())
                .userId(userId)
                .url(url)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
                
    }
}
