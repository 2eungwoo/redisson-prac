package study.redissonprac.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import study.redissonprac.util.TicketLockManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceWithLock implements TicketDecreaseUseCase{
    private final TicketService ticketService;
    private final TicketLockManager lockManager;

    @Override
    public void decrease(final String key, final int count) {
        final String lockName = key + ":lock";
        RLock lock = null;

        try {
            lock = lockManager.tryLock(lockName, 1, 3, TimeUnit.SECONDS);
            if (lock == null) return;

            ticketService.decrease(key, count);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("락 획득 실패", e);
        } finally {
            lockManager.unlock(lock);
        }
    }
}
