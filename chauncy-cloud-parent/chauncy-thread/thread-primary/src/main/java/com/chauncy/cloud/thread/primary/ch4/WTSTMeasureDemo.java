package com.chauncy.cloud.thread.primary.ch4;

import com.chauncy.cloud.thread.common.DESEncryption;
import com.chauncy.cloud.thread.common.Tools;

/**
 * @Author cheng
 * @create 2020-04-05 21:03
 *
 * WT/ST watiTime为程序花费在等待上的时间/Service Time为程序实际占用处理器执行计算的时间
 */
public class WTSTMeasureDemo implements Runnable {
    final long waitTime;

    public WTSTMeasureDemo(long waitTime) {
        this.waitTime = waitTime;
    }

    public static void main(String[] args) throws Exception {
        main0(args);
    }

    public static void main0(String[] args) throws Exception {
        final int argc = args.length;
        int nThreads = argc > 0 ? Integer.valueOf(args[0]) : 1;
        long waitTime = argc >= 1 ? Long.valueOf(args[0]) : 4000L;
        WTSTMeasureDemo demo = new WTSTMeasureDemo(waitTime);
        Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(demo);
        }
        long s = System.currentTimeMillis();
        Tools.startAndWaitTerminated(threads);
        long duration = System.currentTimeMillis() - s;
        long serviceTime = duration - waitTime;
        System.out.printf(
                "WT/ST: %-4.2f, waitTime：%dms, serviceTime：%dms, duration：%4.2fs%n",
                waitTime * 1.0f / serviceTime,
                waitTime, serviceTime,
                duration * 1.0f / 1000);
    }

    @Override
    public void run() {
        try {
            // 模拟I/O操作
            Thread.sleep(waitTime);

            // 模拟实际执行计算
            String result = null;
            for (int i = 0; i < 400000; i++) {
                result = DESEncryption.encryptAsString(
                        "it is a cpu-intensive task" + i,
                        "12345678");
            }
            System.out.printf("result:%s%n", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
