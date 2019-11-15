/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.config;

import com.microservice.auth.common.redis.RedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
    @Value("${redis01.host}")
    private String redis01Host;
    @Value("${redis01.port}")
    private Integer redis01Port;
    @Value("${redis02.host}")
    private String redis02Host;
    @Value("${redis02.port}")
    private Integer redis02Port;
    @Value("${redis03.host}")
    private String redis03Host;
    @Value("${redis03.port}")
    private Integer redis03Port;
    @Value("${redis04.host}")
    private String redis04Host;
    @Value("${redis04.port}")
    private Integer redis04Port;
    @Value("${redis05.host}")
    private String redis05Host;
    @Value("${redis05.port}")
    private Integer redis05Port;
    @Value("${redis06.host}")
    private String redis06Host;
    @Value("${redis06.port}")
    private Integer redis06Port;



    @Bean
    public RedisDao redisDao(){
        Set<HostAndPort> cluster = new HashSet<>();
        cluster.add(new HostAndPort(redis01Host, redis01Port));
        cluster.add(new HostAndPort(redis02Host, redis02Port));
        cluster.add(new HostAndPort(redis03Host, redis03Port));
        cluster.add(new HostAndPort(redis04Host, redis04Port));
        cluster.add(new HostAndPort(redis05Host, redis05Port));
        cluster.add(new HostAndPort(redis06Host, redis06Port));
        return new RedisDao(cluster){};
    }



}
