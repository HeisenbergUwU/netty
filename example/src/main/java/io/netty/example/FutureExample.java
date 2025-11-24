package io.netty.example;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws Exception {
        // 1. 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 2. 提交任务，立即返回 Future
        Future<Long> future = executor.submit(() -> {
            System.out.println("任务开始执行...");
            long sum = 0;
            for (long i = 1; i <= 1000000; i++) {
                sum += i;
            }
            System.out.println("任务执行完成");
            return sum;
        });

        // 3. 主线程可以做其他事情（非阻塞）
        System.out.println("主线程继续执行其他逻辑...");

        // 4. 阻塞等待结果（会暂停当前线程！）
        Long result = future.get(); // ← 关键：阻塞直到任务完成
        System.out.println("计算结果: " + result);

        // 5. 关闭线程池
        executor.shutdown();
    }
}