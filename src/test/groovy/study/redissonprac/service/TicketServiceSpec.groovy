package study.redissonprac.service

import spock.lang.Specification
import spock.lang.Unroll

class TicketServiceSpec extends Specification {

    def "setup"() {
    }

    def "teardown"() {
    }

    @Unroll
    def "a+b는 2다[a: #a,b: #b]"() {
        given:
        // a, b는 where 블록에서 바인딩됨

        when:
        def result = a + b

        then:
        result == 2

        where:
        a | b
        1 | 1
        0 | 2

    }
}
