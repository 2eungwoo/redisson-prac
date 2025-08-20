package study.redissonprac.util;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketRepository {

    private final RedissonClient redissonClient;

    public void setTicket(String key, int amount) {
        redissonClient.<Integer>getBucket(key).set(amount);
    }

    public int getTicket(String key) {
        return redissonClient.<Integer>getBucket(key).get();
    }
}