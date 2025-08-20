package study.redissonprac.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redis.prefix")
public record TicketProperty(String prefix) {
}
