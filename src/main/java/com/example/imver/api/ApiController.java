package com.example.imver.api;

import com.example.imver.cache.SessionCache;
import com.example.imver.cache.UserInfoCache;
import com.example.imver.handler.HashHandler;
import com.example.imver.utils.MD5Utils;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.UUID.randomUUID;

/**
 * @Auther: szp
 * @Date: 2020/4/20 20:34
 * @Description: 服务暴露
 */
@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    private UserInfoCache userInfoCache;
    @Autowired
    private HashHandler hashHandler;
    @Autowired
    private SessionCache sessionCache;
    /**
     * 初始化
     * @param key
     */
    @GetMapping("init")
    public void init(String key) {
        LoadingCache<String, String> cache = userInfoCache.getCache();
        cache.put(key,"");
    }

    /**
     * 获取key~
     *
     * @param key
     * @return
     */
    @SneakyThrows
    @GetMapping("key/skim")
    public String getSkim(String key) {
        String stringToMD5 = MD5Utils.stringToMD5(key);
        LoadingCache<String, String> cache = userInfoCache.getCache();
        String skimKey = cache.get(key);
        if (StringUtils.isEmpty(skimKey)){
            cache.put(key,stringToMD5);
        }
        return skimKey;
    }

    /**
     * 通话
     * @param keyA
     * @param keyB
     * @return
     */
    @SneakyThrows
    @GetMapping("key/cover")
    public void getCover(String keyA, String keyB,String sessionId) {
        LoadingCache<String, String> cache = userInfoCache.getCache();
        String skimKeyA = cache.get(keyA);
        String skimKeyB = cache.get(keyB);
        if (StringUtils.isEmpty(skimKeyA)||StringUtils.isEmpty(skimKeyB)){
            throw new RuntimeException("server is not ready");
        }
        init(sessionId,keyA,keyB);
    }

    @SneakyThrows
    private void init(String sessionId,String keyA,String keyB){
        LoadingCache<String, List<String>> sessionCache = this.sessionCache.getSessionCache();
        List<String> list = sessionCache.get(sessionId);
        if (list.size()<2){
            while (true){
                if (list.size()<2){
                    String key = getKey(keyA, keyB);
                    list.add(key);
                }else {
                    break;
                }
            }
        }
        String key = getKey(keyA, keyB);
        list.add(key);
    }

    /**
     * 服务端所及获取key
     * @param keyA
     * @param keyB
     * @return
     */
    private String getKey(String keyA,String keyB){
        String k = randomUUID().toString();
        String skimKey=k+keyA+keyB;
        return hashHandler.getKeyToHashValue(skimKey);
    }
    /**
     * 验证
     */
    @SneakyThrows
    @GetMapping("ver")
    public String verNumber(String keyB,String keyA,String keyC,String sessionId){
        String keySkim = userInfoCache.getCache().get(keyB);
        if (StringUtils.isEmpty(keySkim)){
            throw new RuntimeException("keyA is not right");
        }
        LoadingCache<String, List<String>> sessionCache = this.sessionCache.getSessionCache();
        List<String> ks = sessionCache.get(sessionId);
        AtomicInteger keyANumber=new AtomicInteger(0);
        AtomicInteger keyCNumber=new AtomicInteger(0);
        for (String k : ks) {
            int number1 = getNumber(k,keyB);
            if (number1>0){
                keyANumber.getAndIncrement();
                continue;
            }
            int number2 = getNumber(k, keyC);
            if (number2>0) {
                keyCNumber.getAndIncrement();
            }
        }
        if (keyANumber.get()>keyCNumber.get()){
            return keyA;
        }else {
            return keyC;
        }
    }

    private int getNumber(String str1,String str2){
        int  total  =  0;
        for  (String  tmp  =  str1;  tmp  !=  null&&tmp.length()>=str2.length();){
            if(tmp.indexOf(str2)  ==  0){
                total  ++;
            }
            tmp  =  tmp.substring(1);
        }
        return total;
    }
}
