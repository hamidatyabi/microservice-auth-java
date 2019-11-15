/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public String md5(String data){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Object stringToObject(String data, Type type){
        try{
            return new Gson().fromJson(data, type);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public byte[] base64ToByte(String base64){
        try{
            return Base64.getDecoder().decode(base64);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public String encodeUrl(String string){
        try{
            return URLEncoder.encode(string, "UTF-8");
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String ListToString(List<String> list, String spliter){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String s : list)
        {
            sb.append(s);
            i++;
            if(i < list.size())
                sb.append(spliter);
        }
        return sb.toString();
    }

    public List<String> stringToList(String text){
        text = text.replaceAll(";" , ",");
        text = text.replaceAll(":" , ",");
        text = text.replaceAll("@" , ",");
        text = text.replaceAll("-" , ",");
        String[] array = text.split(",");
        return Arrays.asList(array);
    }

    public Set<String> stringToSet(String text){
        text = text.replaceAll(";" , ",");
        text = text.replaceAll(":" , ",");
        text = text.replaceAll("@" , ",");
        text = text.replaceAll("-" , ",");
        String[] array = text.split(",");
        return new HashSet<>(Arrays.asList(array));
    }

    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public Long convertStringToLong(String value){
        try{
            Long data = Long.parseLong(value);
            return data;
        }catch (Exception e){
            LOGGER.error("Exception in convert string to long value. Error="+e.getMessage());
        }
        return 0L;
    }
    public Integer convertStringToInteger(String value){
        try{
            Integer data = Integer.parseInt(value);
            return data;
        }catch (Exception e){
            LOGGER.error("Exception in convert string to long value. Error="+e.getMessage(), e);
        }
        return 0;
    }

    public Date convertStringToDateByFormat(String date, String format){
        Date convertDate = null;
        try{
            convertDate = new SimpleDateFormat(format).parse(date);
        }catch (Exception ex){
            LOGGER.error("Exception in convertStringToDateByFormat. DETAIL=" + ex.getMessage());
        }
        return convertDate;
    }

    public LocalDate convertStringToLocalDateByFormat(String date){
        LocalDate convertDate = null;
        try{
            convertDate = LocalDate.parse(date);
        }catch (Exception ex){
            LOGGER.error("Exception in convertStringToDateByFormat. DETAIL=" + ex.getMessage());
        }
        return convertDate;
    }
}
