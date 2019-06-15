package com.ls.adsponsor.constant;

import lombok.Getter;

/**
 * @author lijiayin
 */
@Getter
public enum CreativeMaterialType {

    /**
     * 创意物料类型
     */
    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt"),
    
    ;

    private Integer type;

    private String desc;

    CreativeMaterialType(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }
}
