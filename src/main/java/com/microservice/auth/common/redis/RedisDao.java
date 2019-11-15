/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.common.redis;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class RedisDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    protected JedisCluster jedis;
    public static final String ID_COUNTER = "id_tsp";
    public static final String ID_PREFIX = "id_prefix_tsp";
    public static final String USER_ID_PTR = "user@";
    public static final String USER_USERNAME_PTR = "username:";
    public static final String USER_EMAIL_PTR = "email:";

    protected RedisDao(Set<HostAndPort> cluster) {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setMaxTotal(300);
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxWaitMillis(1500);
        poolConfig.setNumTestsPerEvictionRun(1024);
		poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        poolConfig.setMinEvictableIdleTimeMillis(1800000);
        poolConfig.setSoftMinEvictableIdleTimeMillis(10000);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setBlockWhenExhausted(false);
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        int count = 0;
        for (HostAndPort hostAndPort : cluster) {
            hostAndPorts.add(hostAndPort);
            count++;
        }
        LOGGER.info("Redis connection cluster to "+count+" nods successfully");
        this.jedis = new JedisCluster(hostAndPorts, 2000, 2000, 10000, poolConfig);
    }

    private static final long MSG_ID_COUNTER_LIMIT = 90;
    private static final int MSG_ID_TIME_LEN = 13;
    private static final int MSG_ID_COUNTER_LEN = 2;

    public synchronized String generateId() {
        try {
            String tsp = jedis.get(ID_PREFIX);
            if (StringUtils.isBlank(tsp)) {
                tsp = String.valueOf(System.currentTimeMillis());
                jedis.set(ID_PREFIX, tsp);
            }

            final long counter = jedis.incr(ID_COUNTER);
            if (counter > MSG_ID_COUNTER_LIMIT) {
                jedis.del(ID_PREFIX);
                jedis.del(ID_COUNTER);
            }


            final String c = String.valueOf(counter);

            return StringUtils.rightPad(tsp, MSG_ID_TIME_LEN, '0') +
                    StringUtils.leftPad(c, MSG_ID_COUNTER_LEN, '0');
        } finally {
            
        }

    }
    public <T> T get(String key, Class<T> classOfT){
        try {
            final byte[] redisObj = jedis.get(key).getBytes();
            if (redisObj == null)
                return null;

            return new Gson().fromJson(new String(redisObj), classOfT);

        } finally {

        }
    }
    public boolean set(String key, Object data){
        try {
            jedis.set(key.getBytes(), new Gson().toJson(data).getBytes());
            return true;
        } finally {

        }
    }
    public boolean set(String key, String data){
        try {
            jedis.set(key.getBytes(), data.getBytes());
            return true;
        } finally {

        }
    }

    public boolean set(List<String> keys, Object data){
        try {
            for(String key : keys){
                jedis.set(key.getBytes(), new Gson().toJson(data).getBytes());
            }
            return true;
        } finally {

        }
    }
}
