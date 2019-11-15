/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.config;

import com.microservice.auth.common.entities.User;
import com.microservice.auth.common.repository.UserDao;
import com.microservice.auth.common.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpServletRequest request;


    private final static String _USERNAME = "username";
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, String> params = authentication.getOAuth2Request().getRequestParameters();
        String username = (params.containsKey(_USERNAME))?params.get(_USERNAME):null;
        if(username == null) return accessToken;
        try{
            User user = userDao.getUserByUserName(username);
            final Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("userId", user.getId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        }catch (Exception e){
            LOGGER.error("Exception in getUserByUsername DETAIL:"+e.getMessage(), e);
        }
        return accessToken;
    }
}
