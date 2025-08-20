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
        Object value = redissonClient.getBucket(key).get();
        if (value instanceof Integer i) {
            return i;
        }
        if (value instanceof String s) {
            return Integer.parseInt(s);
        }
        return 0;
        // return redissonClient.<Integer>getBucket(key).get();
    }
}