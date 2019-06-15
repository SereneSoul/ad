package com.ls.adsponsor.service;

import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.vo.CreateUserRequest;
import com.ls.adsponsor.vo.CreateUserResponse;

/**
 * @author lijiayin
 */
public interface IUserService {

    /**
     * 创建用户
     * @param request
     * @return
     * @throws AdException
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
