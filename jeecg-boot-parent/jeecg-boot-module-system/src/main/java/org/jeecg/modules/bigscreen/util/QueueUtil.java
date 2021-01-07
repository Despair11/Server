package org.jeecg.modules.bigscreen.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 存放不同取流结果的队列工具类
 */
@Slf4j
public class QueueUtil<T> {

    //队列大小
    private final int queueSize = 10000;

    private Map<String, BlockingQueue<T>> taskIdBlockingQueueMap = Maps.newConcurrentMap();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();    //注意这个地方

    private String typeName;

    /**
     * 初始化,设置处理消息队列的类
     */

    public boolean init(String taskId, String typeName) {
        lock.writeLock().lock();
        this.typeName = typeName;

        try {
            if (taskIdBlockingQueueMap.get(taskId) != null) {
                log.error("typeName:{},taskId:{}任务已存在", typeName, taskId);
                return false;
            } else {
                BlockingQueue<T> blockingQueue = new LinkedBlockingQueue<>(queueSize);
                taskIdBlockingQueueMap.put(taskId, blockingQueue);
                return true;
            }
        } catch (Exception e) {
            log.error("typeName:" + typeName + ",ProcessPushQueue init error", e);
        } finally {
            lock.writeLock().unlock();
        }

        return false;
    }

    /**
     * 接收的深思服务器数据添加到消息队列中
     *
     * @param value
     */
    public void addQueue(String taskId, T value) {
        try {
            if (value == null || taskId == null) {
                return;
            }
            BlockingQueue<T> byteArrayQueue = taskIdBlockingQueueMap.get(taskId);
            //队列满了就丢失数据
            if (byteArrayQueue != null) {
                boolean success = byteArrayQueue.offer(value);
                if (!success) {
                    log.error("typeName:" + typeName + ",taskId:" + taskId + ", addQueue error ,queue is full");
                }
            }
            //当本地队列满的时候，将消息放入activemq中
        } catch (Exception e) {
            log.error("typeName:" + typeName + ",taskId:" + taskId + ", addQueue error", e);
        }
    }

    /**
     * 从消息队列中取数据进行处理
     */
    public void processQueue(String taskId, DoNext<T> doNext) {
        while (true) {
            BlockingQueue<T> blockingQueue = taskIdBlockingQueueMap.get(taskId);
            //如果任务的队列消失了，那么任务的执行就结束了
            if (blockingQueue != null) {
                T value = null;
                try {
                    value = blockingQueue.take();
                } catch (InterruptedException e) {
                    log.error("error",e);
                }
                if (value == null) {
                    continue;
                }
                try {
                    doNext.doNext(value);
                } catch (Exception e) {
                    log.error("error", e);
                }
            } else {
                log.info("taskId：{}队列处理结束",taskId);
                break;
            }
        }
    }

    /**
     * 销毁数据队列
     */
    public void destroyQueue(String taskId) {
        if (TextUtils.isEmpty(taskId)) {
            return;
        }
        BlockingQueue<T> blockingQueue = taskIdBlockingQueueMap.get(taskId);
        if (blockingQueue != null) {
            blockingQueue.clear();
            taskIdBlockingQueueMap.remove(taskId);
        }
    }

    public interface DoNext<T> {
        void doNext(T byteArray);
    }

    private File getFile(String psFilePath) {
        File file = new File(psFilePath);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }


    /**
     * 将二进制数据写入文件
     */
    private void writeDataToFile(File file, byte[] data) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
