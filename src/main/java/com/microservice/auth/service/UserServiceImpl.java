/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.service;

import com.google.gson.Gson;
import com.microservice.auth.common.entities.User;
import com.microservice.auth.common.repository.UserDao;
import com.microservice.auth.common.repository.UserRepository;
import com.microservice.auth.entities.PrincipalEntity;
import com.microservice.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private UserDao userDao;
    @Autowired
    private Utils utils;
    @Autowired
    private HttpServletRequest request;

    public UserDetails loadUserByUsername(String principalName) throws UsernameNotFoundException {
        User user = null;
        try{
            user = userDao.getUserByUserName(principalName);
        }catch (Exception e){
            LOGGER.error("Exception in loadUserByUsername DETAIL:"+e.getMessage(), e);
        }
        if (user == null)
            throw new UsernameNotFoundException("Invalid username or password.");

        if(!user.getStatus()) {
            throw new UsernameNotFoundException("user is inactive.");
        }
        List<String> roles = new ArrayList<String>();
        roles.add(user.getType().name().toUpperCase());
        org.springframework.security.core.userdetails.User result = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthority(roles));
        return result;

    }

    private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        int i = 0;
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

}
