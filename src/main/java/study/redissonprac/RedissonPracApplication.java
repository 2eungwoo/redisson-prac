package study.redissonprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import study.redissonprac.config.RedisProperty;
import study.redissonprac.config.TicketProperty;

@SpringBootApplication
// @ConfigurationPropertiesScan 혹은 아래처럼 명시
@EnableConfigurationProperties({TicketProperty.class, RedisProperty.class})
public class RedissonPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissonPracApplication.class, args);
    }

}
