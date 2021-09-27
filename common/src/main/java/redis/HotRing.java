package redis;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author:bryan.c
 * Date:2021/9/27
 */
public class HotRing {
    public static void main(String[] args) {
        /**
         * 不需要网络 IO，效率高，维护成本低
         *
         * 缺点：基于JVM内存的一种布隆过滤器，重启即失效，本地内存无法用在分布式场景，不支持大数据量存储
         */
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),100000,0.01);
        bloomFilter.put("10086");

        System.out.println(bloomFilter.mightContain("123456"));
        System.out.println(bloomFilter.mightContain("10086"));


        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.14.104:6379");
        config.useSingleServer().setPassword("123");
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<String> bloomFilterRedis = redisson.getBloomFilter("phoneList");
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%
        bloomFilterRedis.tryInit(100000000L,0.03);
        //将号码10086插入到布隆过滤器中
        bloomFilterRedis.add("10086");

        //判断下面号码是否在布隆过滤器中
        System.out.println(bloomFilterRedis.contains("123456"));//false
        System.out.println(bloomFilterRedis.contains("10086"));//true
    }
}
