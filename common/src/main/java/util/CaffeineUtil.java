package util;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author:bryan.c
 * @Date:2021/3/19
 */
public class CaffeineUtil {
    public final LoadingCache<String,String> cache = Caffeine.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .refreshAfterWrite(15, TimeUnit.SECONDS)
            .removalListener((o, o2, removalCause) -> System.out.println("key:"+ o.toString()+"--value:"+o2.toString()+"--cause"+removalCause.toString()))
            .recordStats()
            .build(this::routerDataLoader);

    public String routerDataLoader(String key) {
        return key+ UUID.randomUUID();
    }

}
