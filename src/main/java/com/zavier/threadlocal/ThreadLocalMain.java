package com.zavier.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TransmittableThreadLocal.Transmitter;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alibaba.ttl.TransmittableThreadLocal.Transmitter.runSupplierWithCaptured;

public class ThreadLocalMain {

    ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newSingleThreadExecutor());

    TransmittableThreadLocal<String> nameThreadLocal = new TransmittableThreadLocal<String>() {
        @Override
        public String copy(String parentValue) {
            // 实现自己的落差
            return super.copy(parentValue);
        }
    };

    @Before
    public void init() {

        executorService.execute(() -> System.out.println("hello"));
        executorService.execute(() -> System.out.println("hello"));
        executorService.execute(() -> System.out.println("hello"));
    }

    @Test
    public void test() {
        nameThreadLocal.set("zheng");

        executorService.execute(() -> {
            final String s = nameThreadLocal.get();
            System.out.println(s);
        });
    }

    @Test
    public void test1() {
        nameThreadLocal.set("hello world");
        // 记录主线程当时的数据快照
        Object captured = Transmitter.capture();

        Callable<String> callable = () -> {
            // 将记录的主线程快照进行重放，并将当前子线程的数据快照进行备份
            Object backup = Transmitter.replay(captured); // (2)
            try {
                System.out.println("Hello");
                return "World";
            } finally {
                // 执行完毕之后，恢复之前备份的子线程快照数据
                Transmitter.restore(backup); // (3)
            }
        };
        executorService.submit(callable);
        executorService.shutdown();
    }

    @Test
    public void test2() {

        Object captured = Transmitter.capture();

        executorService.submit(() -> {
            String result = runSupplierWithCaptured(captured, () -> {
                System.out.println("Hello");
                return "World";
            });
        });
    }

    @Test
    public void test3() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        // the value of this ThreadLocal instance will be transmitted after registered
//        Transmitter.registerThreadLocal(threadLocal, copyLambda);
        Transmitter.registerThreadLocalWithShadowCopier(threadLocal);

        // Then the value of this ThreadLocal instance will not be transmitted after unregistered
        Transmitter.unregisterThreadLocal(threadLocal);
    }
}
