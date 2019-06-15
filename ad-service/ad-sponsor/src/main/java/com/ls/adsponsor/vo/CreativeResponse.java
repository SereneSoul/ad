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
public class CreativeResponse {
    
    private Long id;
    
    private String name;
}
