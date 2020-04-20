package com.example.imver.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: szp
 * @Date: 2020/4/20 22:17
 * @Description: sessioncahche
 */
@Component
@Data
public class SessionCache {
    private LoadingCache<String, List<String>> sessionCache;

    public SessionCache() {
        sessionCache = CacheBuilder.newBuilder()
                //初始化缓存容量
                .initialCapacity(0)
                //最多存放1000个数据,超过最大值：LRU进行清除
                .maximumSize(1000)
                //缓存30分钟
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String key) throws Exception {
                        return new ArrayList<>();
                    }
                });
    }
}
