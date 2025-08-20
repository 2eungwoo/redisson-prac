package study.redissonprac;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import study.redissonprac.util.TicketRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TicketRepository ticketRepository;

    @Override
    public void run(String... args) {
        // 서버 시작 시 event 티켓 100장 세팅
        ticketRepository.setTicket("event", 100);
        System.out.println(">>> Redis 티켓 초기화 완료: event=100");
    }
}
