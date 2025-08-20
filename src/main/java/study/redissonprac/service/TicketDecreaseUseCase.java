package study.redissonprac.service;

public interface TicketDecreaseUseCase {
    void decrease(String key, int count);
}