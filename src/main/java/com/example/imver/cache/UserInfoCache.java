package com.example.imver.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: szp
 * @Date: 2020/4/20 20:49
 * @Description: 沈泽鹏写点注释吧
 */
@Component
@Data
public class UserInfoCache {
    private LoadingCache<String, String> cache;

    public UserInfoCache() {
        cache = CacheBuilder.newBuilder()
                //初始化缓存容量
                .initialCapacity(0)
                //最多存放1000个数据,超过最大值：LRU进行清除
                .maximumSize(1000)
                //缓存30分钟
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return new String();
                    }
                });
    }
}
