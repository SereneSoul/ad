package com.ls.adsearch.index.district;

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
public class UnitDistrictIndex implements IndexAware<String, Set<Long>> {

    /**
     * <province-city, unitId>
     */
    private static Map<String, Set<Long>> districtUnitMap;

    private static Map<Long, Set<String>> unitDistrictMap;

    static {
        districtUnitMap = new ConcurrentHashMap<>();
        unitDistrictMap = new ConcurrentHashMap<>();
    }


    @Override
    public Set<Long> get(String key) {

        if(StringUtils.isBlank(key)){
            return Collections.emptySet();
        }

        Set<Long> result = districtUnitMap.get(key);
        if(CollectionUtils.isEmpty(result)){
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitDistrictIndex before add: {}", unitDistrictMap);

        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key,
                districtUnitMap,
                ConcurrentSkipListSet::new
        );
        unitIdSet.addAll(value);

        for (Long unitId : value){
            Set<String> districtSet = CommonUtils.getOrCreate(
                    unitId,
                    unitDistrictMap,
                    ConcurrentSkipListSet::new
            );
            districtSet.add(key);
        }

        log.info("UnitDistrictIndex after add: {}", unitDistrictMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("district index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitDistrictIndex before delete: {}", unitDistrictMap);

        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key,
                districtUnitMap,
                ConcurrentSkipListSet::new);
        unitIdSet.removeAll(value);

        for (Long unitId : value){
            Set<String> districtSet = CommonUtils.getOrCreate(
                    unitId,
                    unitDistrictMap,
                    ConcurrentSkipListSet::new);
            districtSet.remove(key);
        }

        log.info("UnitDistrictIndex after delete: {}", unitDistrictMap);
    }

    public boolean match(Long unitId, List<String> districts) {
        if(unitDistrictMap.containsKey(unitId)
                && CollectionUtils.isNotEmpty(unitDistrictMap.get(unitId))){

            Set<String> unitDistricts = unitDistrictMap.get(unitId);

            return CollectionUtils.isSubCollection(districts, unitDistricts);
        }
        return false;
    }
}
