package study.redissonprac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.redissonprac.util.TicketRepository;

@Slf4j
@Service("ticketServiceWithNoLock")
@RequiredArgsConstructor
public class TicketServiceWithNoLock implements TicketDecreaseUseCase{

    private final TicketRepository ticketRepository;
    private static final int EMPTY = 0;

    @Override
    public void decrease(final String key, final int count) {
        final String worker = Thread.currentThread().getName();
        final int ticket = ticketRepository.getTicket(key);

        if (ticket <= EMPTY) {
            log.info("[{}] 티켓 없음. 현재 남은 티켓 : {}", worker, ticket);
            return;
        }
        log.info("[{}] 진행중. 현재 남은 티켓 : {}", worker, ticket);
        ticketRepository.setTicket(key, ticket - count);
    }
}
