package com.chauncy.cloud.thread.primary.ch3.cas.case02;

import com.chauncy.cloud.thread.common.Debug;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author cheng
 * @create 2020-04-05 13:41
 *
 * 告警上报线程--工作者线程
 */
public enum AlarmMgr implements Runnable{
    // 保存该类的唯一实例
    INSTANCE;

    private final AtomicBoolean initializating = new AtomicBoolean(false);
    boolean initInProgress;

    AlarmMgr() {
        // 什么也不做
    }

    public void init() {
        // 使用AtomicBoolean的CAS操作确保工作者线程只会被创建（并启动）一次
        if (initializating.compareAndSet(false, true)) {
            Debug.info("initializating...");
            // 创建并启动工作者线程
            new Thread(this).start();
        }
    }

    public int sendAlarm(String message) {
        int result = 0;
        // ...
        return result;
    }

    @Override
    public void run() {

    }
}
