package com.ls.adsponsor.service.impl;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.constant.CommonStatus;
import com.ls.adsponsor.constant.Constants;
import com.ls.adsponsor.entity.AdPlan;
import com.ls.adsponsor.entity.AdUser;
import com.ls.adsponsor.repository.AdPlanRepository;
import com.ls.adsponsor.repository.AdUserRepository;
import com.ls.adsponsor.service.IAdPlanService;
import com.ls.adsponsor.util.CommonUtils;
import com.ls.adsponsor.vo.AdPlanGetRequest;
import com.ls.adsponsor.vo.AdPlanRequest;
import com.ls.adsponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author lijiayin
 */
@Slf4j
@Service
public class AdPlanServiceImpl implements IAdPlanService {
    
    private final AdUserRepository userRepository;
    private final AdPlanRepository planRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository, AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        
        //确保关联的User存在
        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if(!adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        
        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(),
                request.getPlanName());
        if(oldPlan != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        
        AdPlan newPlan = planRepository.save(new AdPlan(
                request.getUserId(), 
                request.getPlanName(),
                CommonUtils.parseStringDate(request.getStartDate()), 
                CommonUtils.parseStringDate(request.getEndDate())
        ));
        
        return AdPlanResponse.builder()
                .id(newPlan.getId())
                .planName(newPlan.getPlanName())
                .build();
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        
        if(!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan adPlan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        
        if(adPlan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        
        if(StringUtils.isNotBlank(request.getPlanName())){
            adPlan.setPlanName(request.getPlanName());
        }
        
        if(StringUtils.isNotBlank(request.getStartDate())){
            adPlan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        
        if(StringUtils.isNotBlank(request.getEndDate())){
            adPlan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }
        
        adPlan.setUpdateTime(new Date());
        
        adPlan = planRepository.save(adPlan);

        return AdPlanResponse.builder()
                .id(adPlan.getId())
                .planName(adPlan.getPlanName())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

        if(!request.deleteValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        
        AdPlan adPlan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        
        if(adPlan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        
        adPlan.setPlanStatus(CommonStatus.INVALID.getStatus());
        adPlan.setUpdateTime(new Date());
        planRepository.save(adPlan);
    }
}
