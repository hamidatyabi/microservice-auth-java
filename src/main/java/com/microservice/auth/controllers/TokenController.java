/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.controllers;
import com.microservice.auth.common.entities.User;
import com.microservice.auth.common.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;
import java.lang.invoke.MethodHandles;
import java.security.Principal;

@RestController
public class TokenController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private UserDao userDao;

    @ResponseBody
    @GetMapping(value = "/token_info")
    public User GetTokenInfo(Principal principal){
        User user = userDao.getUserByUserName(principal.getName());
        if(user == null)
            throw new AuthorizationServiceException("authentication failed");
        user.setPassword(null);
        return user;
    }

}
