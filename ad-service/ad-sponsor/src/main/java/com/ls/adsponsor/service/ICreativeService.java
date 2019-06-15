package com.ls.adsponsor.service;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.vo.CreativeRequest;
import com.ls.adsponsor.vo.CreativeResponse;

/**
 * @author lijiayin
 */
public interface ICreativeService {
    
    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
