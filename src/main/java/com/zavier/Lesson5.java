package com.zavier;

import java.util.ArrayList;
import java.util.List;

/**
 * 有1 元、2 元、5 元和 10 元纸币
 * 共发放10元，有多少种可能
 */
public class Lesson5 {
    public static long[] rewards = {1, 2, 5, 10};

    public static void main(String[] args) {
        int totalReward = 10;
        Lesson5.get1(totalReward, new ArrayList<>());
    }

    public static void get(long totalReward, ArrayList<Long> result) {
        // 当 totalReward = 0 时，证明它是满足条件的解，结束嵌套调用，输出解
        if (totalReward == 0) {
            System.out.println(result);
            return;
        }
        // 当 totalReward < 0 时，证明它不是满足条件的解，不输出
        else if (totalReward < 0) {
            return;
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList<Long> newResult = (ArrayList<Long>) (result.clone());    // 由于有 4 种情况，需要 clone 当前的解并传入被调用的函数
                newResult.add(rewards[i]);                        // 记录当前的选择，解决一点问题
                get(totalReward - rewards[i], newResult);        // 剩下的问题，留给嵌套调用去解决
            }
        }

    }

    public static void get1(long totalReward, List<Long> result) {
        for (int i = 0; i < rewards.length; i++) {
            if (totalReward - rewards[i] >= 0) {
                // 需要带着之前添加进去的元素，继续去探索新的可能
                ArrayList<Long> objects = new ArrayList<>(result);
                objects.add(rewards[i]);
                get(totalReward - rewards[i], objects);
            } else {
                System.out.println(result);
            }
        }
    }
}
