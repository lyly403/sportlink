package com.firstteam.sportsLink;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("redis.2weajp.ng.0001.apn2.cache.amazonaws.com", 6379);
        //return new LettuceConnectionFactory("redis1.tfcivz.ng.0001.apn2.cache.amazonaws.com", 6379);
    }
}
