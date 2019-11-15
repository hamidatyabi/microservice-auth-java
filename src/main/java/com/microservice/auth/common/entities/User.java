/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.common.entities;

import java.util.Date;

public class User {
    public enum UserType {super_admin, administrator, user}

    public static String ID = "id";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String FULL_NAME = "full_name";
    public static String COMPANY_NAME = "company_name";
    public static String EMAIL = "email";
    public static String TYPE = "type";
    public static String CREATE_TIME = "create_time";
    public static String CREDIT = "credit";
    public static String STATUS = "status";


    private Integer id;
    private UserType type;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String companyName;
    private Date createTime;
    private Double credit;
    private Boolean status;


    public User() {
    }

    public User(Integer id, UserType type, String username, String password, String email, String fullName, String companyName, Date createTime, Double credit, Boolean status) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.companyName = companyName;
        this.createTime = createTime;
        this.credit = credit;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", createTime=" + createTime +
                ", credit=" + credit +
                ", status=" + status +
                '}';
    }
}
