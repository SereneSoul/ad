package com.ls.adsearch.index.creativeunit;

import com.ls.adsearch.index.IndexAware;
import com.ls.adsearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author lijiayin
 */
@Slf4j
@Component
public class CreativeUnitIndex implements IndexAware<String, CreativeUnitObject> {

    /**
     * <adId-unitId, CreativeUnitObject>
     */
    private static Map<String, CreativeUnitObject> objectMap;

    /**
     * <adId,unitIds>
     */
    private static Map<Long, Set<Long>> creativeUnitMap;

    /**
     * <unitId,adIds>
     */
    private static Map<Long, Set<Long>> unitCreativeMap;
    
    static {
        objectMap = new ConcurrentHashMap<>();
        creativeUnitMap = new ConcurrentHashMap<>();
        unitCreativeMap = new ConcurrentHashMap<>();
    }
    
    
    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {
        log.info("before add: {}", objectMap);

        objectMap.put(key, value);
        
        Set<Long> unitSet = CommonUtils.getOrCreate(
                value.getAdId(),
                creativeUnitMap,
                ConcurrentSkipListSet::new
        );
        unitSet.add(value.getUnitId());

        Set<Long> creativeSet = CommonUtils.getOrCreate(
                value.getUnitId(),
                unitCreativeMap,
                ConcurrentSkipListSet::new
        );
        creativeSet.add(value.getAdId());
        
        log.info("after add: {}", objectMap);
    }

    @Override
    public void update(String key, CreativeUnitObject value) {
        log.error("creative-unit index can not support update");
    }

    @Override
    public void delete(String key, CreativeUnitObject value) {
        log.info("before delete: {}", objectMap);

        objectMap.remove(key);

        Set<Long> unitSet = CommonUtils.getOrCreate(
                value.getAdId(),
                creativeUnitMap,
                ConcurrentSkipListSet::new
        );
        unitSet.remove(value.getUnitId());

        Set<Long> creativeSet = CommonUtils.getOrCreate(
                value.getUnitId(),
                unitCreativeMap,
                ConcurrentSkipListSet::new
        );
        creativeSet.remove(value.getAdId());
        
        log.info("after delete: {}", objectMap);
    }
}
