/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.common.repository;

import com.microservice.auth.common.entities.User;

public interface UserRepository {
    User getUserById(Integer id);
    User getUserByUserName(String username);
    User getUserByEmail(String email);
}
