package com.example.imver;

import com.example.imver.cache.UserInfoCache;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class ImverApplicationTests {
    @Autowired
    private UserInfoCache userInfoCache;
    @Test
    void contextLoads() throws ExecutionException {
        LoadingCache<String, String> cache = userInfoCache.getCache();
        System.out.println(cache.get("a"));

    }

}
