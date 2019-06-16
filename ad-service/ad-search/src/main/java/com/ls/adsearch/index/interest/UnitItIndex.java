package com.ls.adsearch.index.interest;

import com.ls.adsearch.index.IndexAware;
import com.ls.adsearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author lijiayin
 */
@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {
    
    
    private static Map<String, Set<Long>> itUnitMap;

    private static Map<Long, Set<String>> unitItMap;
    
    static {
        itUnitMap = new ConcurrentHashMap<>();
        unitItMap = new ConcurrentHashMap<>();
    }
    
    
    @Override
    public Set<Long> get(String key) {
        
        if(StringUtils.isBlank(key)){
            return Collections.emptySet();
        }

        Set<Long> result = itUnitMap.get(key);
        if(CollectionUtils.isEmpty(result)){
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitItIndex before add: {}", unitItMap);
        
        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key,
                itUnitMap,
                ConcurrentSkipListSet::new
        );
        unitIdSet.addAll(value);
        
        for (Long unitId : value){
            Set<String> itSet = CommonUtils.getOrCreate(
                    unitId,
                    unitItMap,
                    ConcurrentSkipListSet::new
            );
            itSet.add(key);
        }

        log.info("UnitItIndex after add: {}", unitItMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("it index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitItIndex before delete: {}", unitItMap);

        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key,
                itUnitMap,
                ConcurrentSkipListSet::new);
        unitIdSet.removeAll(value);

        for (Long unitId : value){
            Set<String> itTagSet = CommonUtils.getOrCreate(
                    unitId,
                    unitItMap,
                    ConcurrentSkipListSet::new);
            itTagSet.remove(key);
        }

        log.info("UnitItIndex after delete: {}", unitItMap);
    }

    public boolean match(Long unitId, List<String> itTags) {
        if(unitItMap.containsKey(unitId)
                && CollectionUtils.isNotEmpty(unitItMap.get(unitId))){

            Set<String> unitItTags = unitItMap.get(unitId);

            return CollectionUtils.isSubCollection(itTags, unitItTags);
        }
        return false;
    }
}
