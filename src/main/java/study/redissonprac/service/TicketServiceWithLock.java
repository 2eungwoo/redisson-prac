package study.redissonprac.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import study.redissonprac.util.TicketLockManager;
import study.redissonprac.util.TicketRepository;

@Slf4j
@Service("ticketServiceWithLock")
@RequiredArgsConstructor
public class TicketServiceWithLock implements TicketDecreaseUseCase{
    private final TicketRepository ticketRepository;
    private final TicketLockManager lockManager;
    private static final int EMPTY = 0;

    @Override
    public void decrease(final String key, final int count) {
        final String lockName = key + ":lock";
        final String worker = Thread.currentThread().getName();
        RLock lock = null;

        try {
            lock = lockManager.tryLock(lockName, 1, 3, TimeUnit.SECONDS);
            if (lock == null) return;

            final int ticket = ticketRepository.getTicket(key);

            if (ticket <= EMPTY) {
                log.info("[{}] 티켓 없음. 현재 남은 티켓 : {}", worker, ticket);
                throw new SoldOutException();
            }

            log.info("[{}] 진행중. 현재 남은 티켓 : {}", worker, ticket);
            ticketRepository.setTicket(key, ticket - count);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("락 획득 실패", e);
        } finally {
            lockManager.unlock(lock);

        }
    }
}