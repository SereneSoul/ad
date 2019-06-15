package com.ls.adsponsor.constant;

import lombok.Getter;

/**
 * @author lijiayin
 */
@Getter
public enum CreativeType {

    /**
     * 创业类型
     */
    IMAGE(1, "图片"),
    VIDEO(2, "视频"),
    TEXT(3, "文本"),
    ;

    private Integer type;

    private String desc;

    CreativeType(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }
}
