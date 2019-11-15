/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.common.repository;

import com.microservice.auth.common.entities.Client;
import com.microservice.auth.common.entities.User;
import com.microservice.auth.common.redis.RedisDao;
import com.microservice.auth.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ClientDao implements ClientDetailsService, ClientRegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private Utils utils;

    private static final String QUERY_SELECT_CLEINT_BY_ID = "SELECT clients.* FROM clients WHERE client_id = ? LIMIT 1";
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = jdbcTemplate.queryForObject(
                QUERY_SELECT_CLEINT_BY_ID,
                new Object[]{clientId},
                new RowMapper<Client>() {
                    @Override
                    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Client result = new Client(
                                rs.getString(Client.CLIENT_ID),
                                utils.stringToSet(rs.getString(Client.RESOURCE_IDS)),
                                true,
                                rs.getString(Client.CLIENT_SECRET),
                                true,
                                utils.stringToSet(rs.getString(Client.SCOPE)),
                                utils.stringToSet(rs.getString(Client.AUTHORIZED_GRANT_TYPES)),
                                utils.stringToSet(rs.getString(Client.REGISTERED_REDIRECT_URI)),
                                getAuthority(utils.stringToList(rs.getString(Client.AUTHORITIES))),
                                rs.getInt(Client.ACCESS_TOKEN_VALIDITY_SECONDS),
                                rs.getInt(Client.REFRESH_TOKEN_VALIDITY_SECONDS),
                                true,
                                null
                        );
                        return result;
                    }
                }
        );
        if (client == null) throw new ClientRegistrationException(String.format("Client with id %s not found", clientId));

        return client;
    }

    private Collection<GrantedAuthority> getAuthority(List<String> roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        int i = 0;
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException{

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    @Override
    public void updateClientSecret(String clientId, String clientSecret) throws NoSuchClientException {

    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {

    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }

}
