package study.redissonprac.service

import spock.lang.Specification
import study.redissonprac.util.TicketRepository

class TicketServiceWithNoLockSpec extends Specification {

    def ticketRepository = Mock(TicketRepository)
    def service = new TicketServiceWithNoLock(ticketRepository)

    def "티켓 차감 성공"() {
        given:
        ticketRepository.getTicket("event") >> 10

        when:
        service.decrease("event", 3)

        then:
        1 * ticketRepository.setTicket("event", 7)
    }

    def "티켓 없으면 차감하지 않음"() {
        given:
        ticketRepository.getTicket("event") >> 0

        when:
        service.decrease("event", 2)

        then:
        0 * ticketRepository.setTicket(_, _)
    }
}