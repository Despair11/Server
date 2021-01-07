package org.jeecg.modules.bigscreen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolUtil {
    private static volatile ThreadPoolUtil threadPoolUtil;
    private ExecutorService executorService;

    public void init(int num) {
        executorService = Executors.newFixedThreadPool(num);
    }

    public static ThreadPoolUtil getInstance() {
        if (threadPoolUtil == null) {
            synchronized (ThreadPoolUtil.class) {
                if (threadPoolUtil == null) {
                    threadPoolUtil = new ThreadPoolUtil();
                }
            }
        }
        return threadPoolUtil;
    }


    public void submitTask(Runnable runnable) {
        try {
            executorService.submit(runnable);
        } catch (Exception e) {
            log.error(("线程池执行任务失败"), e);
            new Thread(runnable).start();
        }
    }
}
