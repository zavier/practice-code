package com.zavier.async.discount;

/**
 * 折扣服务
 */
public class Discount {
    public enum Code {
        None(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }

    }
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    // 模拟耗时的计算折扣后价格
    private static double apply(double price, Code code) {
        Shop.delay();
        return price * (100 - code.percentage) / 100;
    }
}
