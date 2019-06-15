package com.ls.adsponsor.controller;

import com.alibaba.fastjson.JSON;
import com.ls.adcommon.exception.AdException;
import com.ls.adsponsor.service.IUserService;
import com.ls.adsponsor.vo.CreateUserRequest;
import com.ls.adsponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijiayin
 */
@Slf4j
@RestController
public class UserOperateController {
    
    private final IUserService userService;

    @Autowired
    public UserOperateController(IUserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}", JSON.toJSONString(request));      
        return userService.createUser(request);
    }
}
