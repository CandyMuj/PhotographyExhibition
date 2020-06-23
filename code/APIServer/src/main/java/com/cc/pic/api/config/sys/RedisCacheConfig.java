package com.cc.pic.api.config.sys;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @ProjectName api
 * @FileName RedisCacheConfig
 * @Description 增强redis
 * @Author CandyMuj
 * @Date 2020/4/19 13:14
 * @Version 1.0
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
    @Resource
    private RedisProperties redisProperties;


    /**
     * 如果不设置序列化，那么存入的数据就是编码后的数据，不方便人为查看和维护
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // fastJson序列化：缺点存入Redis中的内容直接都是明文，确实方便查看，但是不安全
        // value值的序列化采用fastJsonRedisSerializer
        // FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
        // 使用这个序列化，存入的内容是编译后的内容，非明文更安全些
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        RedisProperties.Pool pool = redisProperties.getJedis().getPool();
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait().toMillis());

        return new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout().getNano(), (StrUtil.isNotBlank(redisProperties.getPassword()) ? redisProperties.getPassword() : null), redisProperties.getDatabase());
    }

}