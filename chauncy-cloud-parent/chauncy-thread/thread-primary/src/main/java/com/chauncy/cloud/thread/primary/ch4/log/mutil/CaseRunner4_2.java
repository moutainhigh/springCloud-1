package com.chauncy.cloud.thread.primary.ch4.log.mutil;

import com.chauncy.cloud.thread.common.AppWrapper;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author cheng
 * @create 2020-04-05 20:34
 * 参考命令如下。其中，JVM系统属性x.std.in的值需要根据实际情况修改，将其修改为每行包含一个接口日志文件名的文本文件。<BR>
 * java -Xms96m -Xmx128m -XX:NewSize=64m -XX:SurvivorRatio=32
 * -Dx.std.in="/tmp/in.dat"
 * -Dx.stat.task=io.github.viscent.mtia.ch4.case02.MultithreadedStatTask
 * -Dx.input.buffer=8192 -Dx.block.size=2000
 * io.github.viscent.mtia.ch4.case02.CaseRunner
 */
public class CaseRunner4_2 {
    // 日志文件所在目录
    private static final String LOG_BASE_DIR = "/home/viscent/tmp/tps/";

    public static void main(String[] args) throws Exception {
        AppWrapper.invokeMain0(CaseRunner4_2.class, args, true);
    }

    public static void main0(String[] args) throws Exception {
        int argc = args.length;
        // 根据指定的日志文件创建唯一一个输入流
        InputStream in = createInputStream();
        // 一对请求与响应之间的消息唯一标识的后3位值之差
        int traceIdDiff;
        // 待统计的操作名称
        String expectedOperationName;
        // 可选参数：采样周期（单位：秒）
        int sampleInterval;
        /*
         * 可选参数：指定一个以逗号分割的列表，仅发送给该列表中的设备的请求才会被统计在内。 默认值"*"表示不对外部设备做要求。
         */
        String expectedExternalDeviceList;

        traceIdDiff = argc >= 1 ? Integer.valueOf(args[0]) : 3;
        expectedOperationName = argc >= 2 ? args[1] : "sendSms";
        sampleInterval = argc >= 3 ? Integer.valueOf(args[2]) : 10;
        expectedExternalDeviceList = argc >= 4 ? args[3] : "*";

        // 创建执行统计的任务实例
        Runnable task = createTask(in, sampleInterval, traceIdDiff,
                expectedOperationName, expectedExternalDeviceList);

        // 直接在main线程中执行统计任务
        task.run();
    }

    private static Runnable createTask(InputStream in, int sampleInterval,
                                       int traceIdDiff, String expectedOperationName,
                                       String expectedExternalDeviceList) throws Exception {
        String taskClazz = System.getProperty("x.stat.task");

        taskClazz = null == taskClazz ? "io.github.viscent.mtia.ch4.case02.MultithreadedStatTask"
                : taskClazz;

        Class<?> clazz = Class.forName(taskClazz);
        Constructor<?> constructor = clazz.getConstructor(new Class[] {
                InputStream.class, int.class, int.class, String.class, String.class });

        Runnable st = (Runnable) constructor.newInstance(new Object[] { in,
                sampleInterval, traceIdDiff, expectedOperationName,
                expectedExternalDeviceList });
        return st;
    }

    private static InputStream createInputStream() {
        final AtomicBoolean readerClosed = new AtomicBoolean(false);
        final BufferedReader bfr = new BufferedReader(new InputStreamReader(
                System.in)) {
            @Override
            public void close() throws IOException {
                super.close();
                readerClosed.set(true);
            }
        };
        SequenceInputStream si = new SequenceInputStream(
                new Enumeration<InputStream>() {
                    String fileName = null;

                    @Override
                    public boolean hasMoreElements() {
                        if (readerClosed.get()) {
                            return false;
                        }
                        try {
                            fileName = bfr.readLine();
                            if (null == fileName) {
                                bfr.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            fileName = null;
                        }
                        return null != fileName;
                    }

                    @Override
                    public InputStream nextElement() {
                        InputStream in = null;
                        if (null != fileName) {
                            try {
                                in = new FileInputStream(new File(LOG_BASE_DIR + fileName));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        return in;
                    }

                });

        return si;

    }

}

