package com.zavier.async.shop;

import java.util.concurrent.Future;

public class SimpleGetPrice {

    /**
     * 简单使用商家提供的异步API
     * @param args
     */
    public static void main(String[] args) {
        final Shop shop = new Shop("BestShop");
        final long start = System.nanoTime();
        final Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // todo 此时可以做一些其他事情

        try {
            final double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
