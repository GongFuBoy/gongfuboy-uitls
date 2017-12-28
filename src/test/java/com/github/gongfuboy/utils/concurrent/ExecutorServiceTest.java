package com.github.gongfuboy.utils.concurrent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程测试
 *
 * @author GongFuBoy
 * @date 2017/12/21
 * @time 17:47
 */
public class ExecutorServiceTest {

    private final Log logger = LogFactory.getLog(getClass());

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        TempCallable tempCallable = new TempCallable();
        Future<Object> future = executorService.submit(tempCallable);
        long start = System.currentTimeMillis();
        logger.info("准备获取结果集：" + start);
        try {
            Object o = future.get();
        } catch (Exception e) {
        }

        long cost = System.currentTimeMillis() - start;
        logger.info("获取到结果集：" + cost);
    }

}
