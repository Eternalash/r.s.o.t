package util;

import com.github.benmanes.caffeine.cache.*;
import lombok.NonNull;
import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @Author:bryan.c
 * @Date:2021/3/19
 */
public class CaffeineUtilTest {
    private final LoadingCache<Object, Object> cache = Caffeine.newBuilder()
            //最后一次写入后经过固定时间过期
            .expireAfterWrite(2, TimeUnit.SECONDS)
            //创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<Object, Object>() {
                @Override
                public void onRemoval(Object o, Object o2, RemovalCause removalCause) {
                    System.out.println(o+"-"+o2+"-"+removalCause.toString());
                }
            })
            .build(new CacheLoader<Object, Object>() {
                //单个 key 的值加载
                @Nullable
                @Override
                public Object load(@NonNull Object key) throws Exception {
                    return key + "_" + System.currentTimeMillis();
                }

                //如果没有重写 loadAll 方法则默认的 loadAll 回循环调用 load 方法，一般重写优化性能
                @Override
                public @NonNull Map<Object, Object> loadAll(@NonNull Iterable<?> keys) throws Exception {
                    Map<Object, Object> data = new HashMap<>();
                    for (Object key : keys) {
                        data.put(key, key + "_all_" + System.currentTimeMillis());
                    }
                    return data;
                }
            });


    @Test
    @Cacheable
    public void routerDataLoader() throws InterruptedException, IOException {
        String key1 = "key1";

        //获取 key1 对应的值
        Object value = cache.get(key1);
        System.out.println(value);

        System.out.println(cache.get(key1));
        sleep(1001);

        //批量获取
        Map<Object, Object> all = cache.getAll(Arrays.asList("key1", "key2", "key3"));
        System.out.println(all);
        all = cache.getAll(Arrays.asList("key1", "key2", "key3"));
        System.out.println(all);
        sleep(2001);
        all = cache.getAll(Arrays.asList("key1", "key2", "key3"));
        System.out.println("expireAfterWrite");
        System.out.println(all!=null?all:"null");
    }
}