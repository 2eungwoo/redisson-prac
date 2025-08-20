package study.redissonprac.service

import spock.lang.Specification
import org.redisson.api.RLock
import study.redissonprac.util.TicketLockManager
import study.redissonprac.util.TicketRepository

import java.util.concurrent.TimeUnit

class TicketServiceWithLockSpec extends Specification {

    def ticketRepository = Mock(TicketRepository)
    def lockManager = Mock(TicketLockManager)
    def service = new TicketServiceWithLock(ticketRepository, lockManager)

    def "락 획득 성공시 티켓 차감"() {
        given:
        def lock = Mock(RLock)
        lock.isLocked() >> true
        lock.isHeldByCurrentThread() >> true
        // lockManager.tryLock(*_) >> lock => 모든 인자 다 떄려넣는 방법
        lockManager.tryLock("event:lock", 1L, 3L, TimeUnit.SECONDS) >> lock
        ticketRepository.getTicket("event") >> 5

        when:
        service.decrease("event", 2)

        then:
        1 * ticketRepository.setTicket("event", 3)
        1 * lockManager.unlock(_ as RLock)
    }

    def "락 획득 실패시 아무 일도 안함"() {
        given:
        lockManager.tryLock("event:lock", _ as long, _ as long, _ as TimeUnit) >> null

        when:
        service.decrease("event", 1)

        then:
        0 * ticketRepository._
    }

    def "티켓이 없으면 차감하지 않음"() {
        given:
        def lock = Mock(RLock)
        lock.isLocked() >> true
        lock.isHeldByCurrentThread() >> true
        lockManager.tryLock("event:lock", 1L, 3L, TimeUnit.SECONDS) >> lock
        ticketRepository.getTicket("event") >> 0

        when:
        service.decrease("event", 1)

        then:
        0 * ticketRepository.setTicket(_, _)
        1 * lockManager.unlock(_ as RLock)
    }
}