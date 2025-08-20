package study.redissonprac.config;


import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RedissonConfig {

    private final RedisProperties redisProperty;

    @Bean(destroyMethod = "shutdown") // 빈 종료 시 shutdown 호출 - redisson 문서에서 권장
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
            .setAddress(
                String.format("redis://%s:%d", redisProperty.getHost(), redisProperty.getPort()))
            .setConnectionMinimumIdleSize(10) // 디폴트 커넥션 풀
            .setConnectionPoolSize(64)
            .setRetryAttempts(3);

        return Redisson.create(config);
    }
}