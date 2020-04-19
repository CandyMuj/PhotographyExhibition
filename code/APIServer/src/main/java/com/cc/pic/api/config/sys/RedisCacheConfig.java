package com.cc.pic.api.config.sys;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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

}