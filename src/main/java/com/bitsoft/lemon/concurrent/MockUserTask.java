package com.bitsoft.lemon.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MockUserTask implements Runnable {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        boolean result = hashOperations.putIfAbsent("all:stu", "name", "jameszhang");
        log.info("result:{}", result);
        countDownLatch.countDown();
    }
}
