package study.redissonprac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.redissonprac.service.TicketServiceWithLock;
import study.redissonprac.service.TicketServiceWithNoLock;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceWithLock ticketServiceWithLock;
    private final TicketServiceWithNoLock ticketServiceWithNoLock;

    /**
     * 분산락 적용 O
     */
    @PostMapping("/tickets/lock/decrease")
    public String decreaseWithLock(@RequestParam String key, @RequestParam int count) {
        ticketServiceWithLock.decrease(key, count);
        return "with-lock decrease 요청 완료";
    }

    /**
     * 분산락 적용 X
     */
    @PostMapping("/tickets/nolock/decrease")
    public String decreaseWithoutLock(@RequestParam String key, @RequestParam int count) {
        ticketServiceWithNoLock.decrease(key, count);
        return "no-lock decrease 요청 완료";
    }
}
