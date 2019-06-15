package com.ls.adsponsor.service;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.vo.*;

/**
 * @author lijiayin
 */
public interface IAdUnitService {
    
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;
    
    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;
    
    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}
