package com.example.imver.handler.impl;

import com.example.imver.handler.HashHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Auther: szp
 * @Date: 2020/4/16 10:39
 * @Description: 散列算法
 */
@Component
public class HashHandlerImpl implements HashHandler {
    /**
     * 通过散列函数传化为key
     * @param key
     * @return
     */
    @Override
    public String getKeyToHashValue(String key) {
        hash(key);
        return hash(key);
    }

    private  final String hash(Object key) {
        int h;
        Integer end= (key == null) ? 0 : (h = hashCode(key)) ^ (h >>> 16);
        return end.toString();
    }
    
    public int hashCode(Object objectKey) {
        String key=objectKey.toString();
        if (StringUtils.isEmpty(key)){
            throw new RuntimeException("key can not be null");
        }
        char[] charArr = key.toCharArray();
        int hash = 0;

        for(char c : charArr) {
            hash = hash * 131 + c;
        }
        return hash;
    }
}
