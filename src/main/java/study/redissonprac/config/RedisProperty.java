package study.redissonprac.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.redis")
public record RedisProperty(String host, int port) {

}
