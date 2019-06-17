package com.zavier.async.discount;

import java.util.Random;
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

    /**
     * 模拟一个商家提供的同步查询价格接口
     * @param product
     * @return 店铺名称:价格:折扣类型
     */
    public String getPrice(String product) {
        final double price = calculatePrice(product);
        // 随机获取一个折扣
        final Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
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
