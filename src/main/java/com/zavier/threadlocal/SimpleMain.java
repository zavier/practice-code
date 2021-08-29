package com.zavier.threadlocal;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SimpleMain {


    private Executor executor = Executors.newSingleThreadExecutor();

    public static ThreadLocal<String> USER_NAME_THREAD_LOCAL = new ThreadLocal<>();

    @Test
    public void test() {
        USER_NAME_THREAD_LOCAL.set("zheng");
        executor.execute(new ThreadLocalRunnable());
    }

    static class ThreadLocalRunnable implements Runnable {

        private String userName;

        public ThreadLocalRunnable() {
            this.userName = USER_NAME_THREAD_LOCAL.get();
        }

        @Override
        public void run() {
            try {
                USER_NAME_THREAD_LOCAL.set(userName);
                // todo 业务逻辑
                System.out.println("userName: " + USER_NAME_THREAD_LOCAL.get());
            } finally {
                USER_NAME_THREAD_LOCAL.remove();
            }
        }
    }
}
