package com.bitsoft.lemon.concurrent;

import com.bitsoft.lemon.base.BaseUnitTest;
import com.bitsoft.lemon.utils.SpringUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
public class MockUserTaskTest extends BaseUnitTest {
    @Test
    public void multiThreadTest() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Integer> list = Lists.newArrayList(100);
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        CountDownLatch countDownLatch = new CountDownLatch(100);
        List<CompletableFuture> completableFutureList = list.stream().map(num -> CompletableFuture.runAsync(() -> {
                    MockUserTask mockUserTask = SpringUtils.getContext().getBean(MockUserTask.class);
                    mockUserTask.setCountDownLatch(countDownLatch);
                    mockUserTask.run();
                }, threadPool))
                .collect(Collectors.toList());
        completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        boolean result = true;
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("error", e);
            result = false;
        }
        Assertions.assertTrue(result);
    }
}