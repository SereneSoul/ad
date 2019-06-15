package com.ls.adsponsor.service.impl;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.constant.Constants;
import com.ls.adsponsor.entity.AdPlan;
import com.ls.adsponsor.entity.AdUnit;
import com.ls.adsponsor.entity.unitcondition.AdUnitDistrict;
import com.ls.adsponsor.entity.unitcondition.AdUnitIt;
import com.ls.adsponsor.entity.unitcondition.AdUnitKeyword;
import com.ls.adsponsor.entity.unitcondition.CreativeUnit;
import com.ls.adsponsor.repository.AdPlanRepository;
import com.ls.adsponsor.repository.AdUnitRepository;
import com.ls.adsponsor.repository.CreativeRepository;
import com.ls.adsponsor.repository.unitcondition.AdUnitDistrictRepository;
import com.ls.adsponsor.repository.unitcondition.AdUnitItRepository;
import com.ls.adsponsor.repository.unitcondition.AdUnitKeywordRepository;
import com.ls.adsponsor.repository.unitcondition.CreativeUnitRepository;
import com.ls.adsponsor.service.IAdUnitService;
import com.ls.adsponsor.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lijiayin
 */
@Slf4j
@Service
public class AdUnitServiceImpl implements IAdUnitService {
    
    private final AdUnitRepository unitRepository;
    private final AdPlanRepository planRepository;
    private final AdUnitKeywordRepository unitKeywordRepository;
    private final AdUnitItRepository unitItRepository;
    private final AdUnitDistrictRepository unitDistrictRepository;
    private final CreativeRepository creativeRepository;
    private final CreativeUnitRepository creativeUnitRepository;

    @Autowired
    public AdUnitServiceImpl(AdUnitRepository unitRepository,
                             AdPlanRepository planRepository,
                             AdUnitKeywordRepository unitKeywordRepository,
                             AdUnitItRepository unitItRepository,
                             AdUnitDistrictRepository unitDistrictRepository, 
                             CreativeRepository creativeRepository, 
                             CreativeUnitRepository creativeUnitRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        if(!adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdUnit oldUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if(oldUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }
        
        AdUnit newUnit = unitRepository.save(new AdUnit(
                request.getPlanId(),
                request.getUnitName(),
                request.getPositionType(),
                request.getBudget()
        ));

        return AdUnitResponse.builder()
                .id(newUnit.getId())
                .unitName(newUnit.getUnitName())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        
        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        
        List<AdUnitKeyword> adUnitKeywords = new ArrayList<>();
        request.getUnitKeywords().forEach(unitKeyword -> 
            adUnitKeywords.add(AdUnitKeyword.builder()
                    .unitId(unitKeyword.getUnitId())
                    .keyword(unitKeyword.getKeyword())
                    .build())
        );
        List<Long> ids = unitKeywordRepository.saveAll(adUnitKeywords).stream()
                .map(AdUnitKeyword::getId).collect(Collectors.toList());
        
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitIt> adUnitIts = new ArrayList<>();
        request.getUnitIts().forEach(unitIt ->
                adUnitIts.add(AdUnitIt.builder()
                        .unitId(unitIt.getUnitId())
                        .itTag(unitIt.getItTag())
                        .build())
        );
        List<Long> ids = unitItRepository.saveAll(adUnitIts).stream()
                .map(AdUnitIt::getId).collect(Collectors.toList());
        
        return new AdUnitItResponse(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitDistricts = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitDistricts)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitDistrict> adUnitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(unitDistrict ->
                adUnitDistricts.add(AdUnitDistrict.builder()
                        .unitId(unitDistrict.getUnitId())
                        .province(unitDistrict.getProvince())
                        .city(unitDistrict.getCity())
                        .build())
        );
        List<Long> ids = unitDistrictRepository.saveAll(adUnitDistricts).stream()
                .map(AdUnitDistrict::getId).collect(Collectors.toList());
        
        return new AdUnitDistrictResponse(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> creativeIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());
        if(!isRelatedCreativeExist(creativeIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(creativeUnitItem ->
                creativeUnits.add(CreativeUnit.builder()
                        .unitId(creativeUnitItem.getUnitId())
                        .creativeId(creativeUnitItem.getCreativeId())
                        .build()));
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());
        
        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds){
        
        if(CollectionUtils.isEmpty(unitIds)){
            return false;
        }
        
        return unitRepository.findAllById(unitIds).size() 
                == new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds){

        if(CollectionUtils.isEmpty(creativeIds)){
            return false;
        }

        return creativeRepository.findAllById(creativeIds).size()
                == new HashSet<>(creativeIds).size();
    }
}
