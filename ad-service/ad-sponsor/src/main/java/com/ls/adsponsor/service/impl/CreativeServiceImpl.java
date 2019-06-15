package com.ls.adsponsor.service.impl;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.entity.Creative;
import com.ls.adsponsor.repository.CreativeRepository;
import com.ls.adsponsor.service.ICreativeService;
import com.ls.adsponsor.vo.CreativeRequest;
import com.ls.adsponsor.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijiayin
 */
@Slf4j
@Service
public class CreativeServiceImpl implements ICreativeService {
    
    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        
        Creative creative = creativeRepository.save(request.convertToEntity());
        
        return CreativeResponse.builder()
                .id(creative.getId())
                .name(creative.getName())
                .build();
    }
}
