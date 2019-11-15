/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.common.repository;

import com.microservice.auth.common.entities.User;
import com.microservice.auth.common.redis.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDao")
public class UserDao implements UserRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisDao redisDao;

    private static final String QUERY_SELECT_USERS_BY_ID = "SELECT users.* FROM users WHERE id = ? LIMIT 1";
    @Override
    public User getUserById(Integer id) {
        User user = redisDao.get(RedisDao.USER_ID_PTR + id, User.class);
        if(user != null) return user;
        return jdbcTemplate.queryForObject(
                QUERY_SELECT_USERS_BY_ID,
                new Object[]{id},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User userObject = new User(
                                rs.getInt(User.ID),
                                User.UserType.valueOf(rs.getString(User.TYPE)),
                                rs.getString(User.USERNAME),
                                rs.getString(User.PASSWORD),
                                rs.getString(User.EMAIL),
                                rs.getString(User.FULL_NAME),
                                rs.getString(User.COMPANY_NAME),
                                rs.getTimestamp(User.CREATE_TIME),
                                rs.getDouble(User.CREDIT),
                                rs.getBoolean(User.STATUS)
                        );
                        redisDao.set(RedisDao.USER_ID_PTR + userObject.getId(), String.valueOf(userObject.getId()));
                        redisDao.set(RedisDao.USER_USERNAME_PTR + userObject.getUsername(), userObject);
                        redisDao.set(RedisDao.USER_EMAIL_PTR + userObject.getEmail(), userObject);
                        return userObject;
                    }
                }
        );
    }

    private static final String QUERY_SELECT_USERS_BY_USERNAME = "SELECT users.* FROM users WHERE username = ? LIMIT 1";
    @Override
    public User getUserByUserName(String username) {
        return jdbcTemplate.queryForObject(
                QUERY_SELECT_USERS_BY_USERNAME,
                new Object[]{username},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User userObject = new User(
                                rs.getInt(User.ID),
                                User.UserType.valueOf(rs.getString(User.TYPE)),
                                rs.getString(User.USERNAME),
                                rs.getString(User.PASSWORD),
                                rs.getString(User.EMAIL),
                                rs.getString(User.FULL_NAME),
                                rs.getString(User.COMPANY_NAME),
                                rs.getTimestamp(User.CREATE_TIME),
                                rs.getDouble(User.CREDIT),
                                rs.getBoolean(User.STATUS)
                        );
                        redisDao.set(RedisDao.USER_ID_PTR + userObject.getId(), String.valueOf(userObject.getId()));
                        redisDao.set(RedisDao.USER_USERNAME_PTR + userObject.getUsername(), userObject);
                        redisDao.set(RedisDao.USER_EMAIL_PTR + userObject.getEmail(), userObject);
                        return userObject;
                    }
                }
        );
    }

    private static final String QUERY_SELECT_USERS_BY_EMAIL = "SELECT users.* FROM users WHERE email = ? LIMIT 1";
    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.queryForObject(
                QUERY_SELECT_USERS_BY_EMAIL,
                new Object[]{email},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User userObject = new User(
                                rs.getInt(User.ID),
                                User.UserType.valueOf(rs.getString(User.TYPE)),
                                rs.getString(User.USERNAME),
                                rs.getString(User.PASSWORD),
                                rs.getString(User.EMAIL),
                                rs.getString(User.FULL_NAME),
                                rs.getString(User.COMPANY_NAME),
                                rs.getTimestamp(User.CREATE_TIME),
                                rs.getDouble(User.CREDIT),
                                rs.getBoolean(User.STATUS)
                        );
                        redisDao.set(RedisDao.USER_ID_PTR + userObject.getId(), String.valueOf(userObject.getId()));
                        redisDao.set(RedisDao.USER_USERNAME_PTR + userObject.getUsername(), userObject);
                        redisDao.set(RedisDao.USER_EMAIL_PTR + userObject.getEmail(), userObject);
                        return userObject;
                    }
                }
        );
    }
}
