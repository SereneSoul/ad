package com.ls.adsponsor.service.impl;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.constant.Constants;
import com.ls.adsponsor.entity.AdUser;
import com.ls.adsponsor.repository.AdUserRepository;
import com.ls.adsponsor.service.IUserService;
import com.ls.adsponsor.util.CommonUtils;
import com.ls.adsponsor.vo.CreateUserRequest;
import com.ls.adsponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lijiayin
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    
    private final AdUserRepository userRepository;
    
    @Autowired
    private UserServiceImpl(AdUserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = userRepository.findByUsername(request.getUsername());
        
        if(oldUser != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_USER_ERROR);
        }
        
        AdUser newUser = userRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())));
        
        return CreateUserResponse.builder()
                .userId(newUser.getId())
                .username(newUser.getUsername())
                .token(newUser.getToken())
                .createTime(newUser.getCreateTime())
                .updateTime(newUser.getUpdateTime())
                .build();
    }
}
