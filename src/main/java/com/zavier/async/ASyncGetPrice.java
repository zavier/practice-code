package com.zavier.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class ASyncGetPrice {
    /**
     * 假设商家只提供了同步查询方法，我们需要查询多个商家的价格
     * @param args
     */
    public static void main(String[] args) {
        final ASyncGetPrice getPriceV2 = new ASyncGetPrice();
        getPriceV2.findPrices1("myphone27S");
        getPriceV2.findPrices2("myphone27S");
        getPriceV2.findPrices3("myphone27S");
        getPriceV2.findPrices4("myphone27S");


    }

    int num = 10;

    List<Shop> shops = new ArrayList<>();

    {
        for (int i = 0; i < num; i++) {
            final Shop bestPrice = new Shop("BestPrice");
            shops.add(bestPrice);
        }
    }

    /**
     * 最简单的同步方法，依次查询
     * @param product
     * @return
     */
    public List<String> findPrices1(String product) {
        long start = System.nanoTime();
        final List<String> result = shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
        System.out.println(result);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        return result;
    }

    /**
     * 使用并行流方法查询
     * @param product
     * @return
     */
    public List<String> findPrices2(String product) {
        long start = System.nanoTime();
        final List<String> result = shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
        System.out.println(result);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        return result;
    }

    /**
     * 使用CompletableFuture异步调用(与findPrices2耗时相近～～)
     * @param product
     * @return
     */
    public List<String> findPrices3(String product) {
        long start = System.nanoTime();
        final List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getShopName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());
        final List<String> result = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println(result);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        return result;
    }

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    });

    /**
     * 线程数 = 核数 * 期望CPU使用率(0~1) * 等待时间与计算时间的比率(此例子中可假设为99/1，近似100)
     * 使用指定线程池的CompletableFuture异步调用
     * @param product
     * @return
     */
    public List<String> findPrices4(String product) {
        long start = System.nanoTime();
        // 使用指定的线程池
        final List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getShopName() + " price is " + shop.getPrice(product), executor))
                .collect(Collectors.toList());
        final List<String> result = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println(result);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        return result;
    }
}
