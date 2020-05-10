package com.cc.pic.api.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * demoClass: DemoThreadPool
 * <p>
 * 这是一个用于实现后端异步请求的[线程池]
 * <p/>
 * - 他可以在不影响主程序的正常执行返回的情况下，在新的线程中执行其他复杂繁琐的任务
 *
 * @Title 线程池
 * @FileName ThreadPool
 * @Author CandyMuj
 * @Date 2019/6/26 10:18
 * @Version 1.0
 */
public class ThreadPool {
    private static ExecutorService executor = null;

    private ThreadPool() {
    }

    public static ExecutorService getInstance() {
        if (executor == null) {
            System.out.println("initialize ThreadPool...");
            executor = Executors.newCachedThreadPool();
        }
        return executor;
    }

}
