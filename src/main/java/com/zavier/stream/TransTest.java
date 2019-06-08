package com.zavier.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 为了方便，这里就直接使用了junit的测试方法
 */
public class TransTest {
    private List<Transaction> transactions = new ArrayList<>();

    @Before
    public void setUp() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions.add(new Transaction(brian, 2011, 300));
        transactions.add(new Transaction(raoul, 2012, 1000));
        transactions.add(new Transaction(raoul, 2011, 400));
        transactions.add(new Transaction(mario, 2012, 710));
        transactions.add(new Transaction(mario, 2012, 700));
        transactions.add(new Transaction(alan, 2012, 950));
    }

    /**
     * 1. 找出2011年发生的所有交易，并按交易额排序
     */
    @Test
    public void sort2011Trade() {
        List<Transaction> collect = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 2. 找出交易员都在哪些不同的城市工作过
     */
    @Test
    public void testDistinctCity() {
        List<String> citys = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(citys);
    }

    /**
     * 3. 按姓名排序的所有剑桥交易员
     */
    @Test
    public void sortAllCambridgeTrader() {
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equalsIgnoreCase(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traders);
    }

    /**
     * 4. 排序所有交易员姓名,拼成字符串
     */
    @Test
    public void sortTraderName() {
        String traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("", (s1, s2) -> s1 + s2);
        System.out.println(traderNames);
    }

    /**
     * 5. 是否有米兰的交易员
     */
    @Test
    public void testHasMilanTrader() {
        boolean milan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("Milan"));
        System.out.println(milan);
    }

    /**
     * 6. 打印剑桥交易员的所有交易额
     */
    @Test
    public void allCambridgeTraderValue() {
        long count = transactions.stream()
                .filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(count);
    }

    /**
     * 7. 查询出最高的交易额
     */
    @Test
    public void testMaxTradeValue() {
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        max.ifPresent(System.out::println);

        Optional<Transaction> collect = transactions.stream()
                .collect(Collectors.reducing((t1, t2) -> t1.getValue() > t2.getValue() ? t1 : t2));
        System.out.println(collect);
    }

    /**
     * 8. 查询出最小的交易额
     */
    @Test
    public void testMinTradeValue() {
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo);
        min.ifPresent(System.out::println);
    }

    @Test
    public void testGroup() {
        Map<Integer, Transaction> collect = transactions.stream()
                .collect(Collectors.toMap(Transaction::getValue, Function.identity()));
        System.out.println(collect);

    }
}
