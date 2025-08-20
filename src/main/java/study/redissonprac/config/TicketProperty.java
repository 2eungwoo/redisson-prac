package study.redissonprac.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redis.ticket")
public record TicketProperty(String prefix) {
}
