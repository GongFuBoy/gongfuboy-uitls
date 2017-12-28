package com.github.gongfuboy.utils.concurrent;

import java.util.concurrent.Callable;

/**
 * @author GongFuBoy
 * @date 2017/12/21
 * @time 17:48
 */
public class TempCallable implements Callable<Object>{

    private boolean flag = true;

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000 * 3l);
        if (flag) {
            throw new RuntimeException();
        }
        Thread.sleep(1000 * 2l);
        return null;
    }
}
