package org.jeecg.modules.bigscreen.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
public class CacheUtil {


    private ReentrantLock reentrantLock = new ReentrantLock();
}
