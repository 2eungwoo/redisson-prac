package study.redissonprac.service;

public class SoldOutException extends RuntimeException {

    public SoldOutException() {
        super("티켓이 모두 소진되었습니다.");
    }
}