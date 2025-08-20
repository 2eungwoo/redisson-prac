package study.redissonprac.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.redissonprac.config.TicketProperty;

@Component
@RequiredArgsConstructor
public class KeyResolver {

    private final TicketProperty ticketProperty;

    public String resolve(String domain, String keyId) {
        final String prefix = ticketProperty.prefix() + ":" + domain + ":%s";
        return String.format(prefix, keyId);
    }
}