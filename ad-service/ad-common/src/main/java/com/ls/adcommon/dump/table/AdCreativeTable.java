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
public class AdCreativeTable {
    
    private Long adId;
    
    private String name;
    
    private Integer type;
    
    private Integer materialType;
    
    private Integer height;
    
    private Integer width;
    
    private Integer auditStatus;
    
    private String adUrl;
}
