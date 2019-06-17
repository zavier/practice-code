package com.zavier.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 商家提供的功能
 */
public class Shop {

    private Random random = new Random();

    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    /**
     * 模拟一个商家提供的同步查询价格接口(可能比较耗时)
     * @param product
     * @return
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 商家提供的异步查询价格接口
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice =  new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
                // 在调用了complete后，即使发生异常，也会正常返回值
            } catch (Exception e) {
                // 需要捕获执行过程中发生的异常
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    /**
     * 等同于 getPriceAsync 方法，提供了异常处理等
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        // 模拟异常情况
//        if (true) {
//            throw new RuntimeException("XX");
//        }
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
