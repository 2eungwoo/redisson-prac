package study.redissonprac.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {
    private String name;
    private String keyId;
    private int amount;

}