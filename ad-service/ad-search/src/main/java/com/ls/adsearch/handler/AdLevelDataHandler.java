package com.ls.adsearch.handler;

import com.ls.adcommon.dump.table.AdCreativeTable;
import com.ls.adcommon.dump.table.AdPlanTable;
import com.ls.adcommon.dump.table.AdUnitTable;
import com.ls.adsearch.index.DataTable;
import com.ls.adsearch.index.IndexAware;
import com.ls.adsearch.index.adplan.AdPlanIndex;
import com.ls.adsearch.index.adplan.AdPlanObject;
import com.ls.adsearch.index.adunit.AdUnitIndex;
import com.ls.adsearch.index.adunit.AdUnitObject;
import com.ls.adsearch.index.creative.CreativeIndex;
import com.ls.adsearch.index.creative.CreativeObject;
import com.ls.adsearch.mysql.constant.OpType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lijiayin
 */
@Slf4j
public class AdLevelDataHandler {
    
    public static void handleLevel2(AdPlanTable planTable, OpType type){
        AdPlanObject planObject = new AdPlanObject(
                planTable.getId(),
                planTable.getUserId(),
                planTable.getPlanStatus(),
                planTable.getStartDate(),
                planTable.getEndDate()
        );
        handleBinlogEvent(
                DataTable.of(AdPlanIndex.class),
                planObject.getPlanId(),
                planObject,
                type
        );
    }
    
    public static void handleLevel2(AdCreativeTable creativeTable, OpType type){
        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }
    
    public static void handleLevel3(AdUnitTable unitTable, OpType type){
        
        AdPlanObject adPlanObject = DataTable.of(
                AdPlanIndex.class
        ).get(unitTable.getPlanId());
        
        if(adPlanObject == null){
            log.error("handlerLevel3 found AdPlanObject error: {}",
                    unitTable.getPlanId());
            return;
        }

        AdUnitObject adUnitObject = new AdUnitObject(
                unitTable.getUnitId(),
                unitTable.getUnitStatus(),
                unitTable.getPositionType(),
                unitTable.getPlanId(),
                adPlanObject
        );
        
        handleBinlogEvent(
                DataTable.of(AdUnitIndex.class),
                adUnitObject.getUnitId(),
                adUnitObject,
                type
        );
    }
    
    
    private static <K, V> void handleBinlogEvent(IndexAware<K, V> index,
                                                 K key,
                                                 V value,
                                                 OpType type){
        switch (type) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }
    }
}
