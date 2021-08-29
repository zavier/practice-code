package com.zavier.spring;

import com.zavier.spring.simple.ClassA;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        // 1.创建一个BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(beanPostProcessor);

// 2.创建读取Bean定义的Reader
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
// 3.使用Reader来读取指定的资源信息
        ClassPathResource resource = new ClassPathResource("spring-bean.xml");
        beanDefinitionReader.loadBeanDefinitions(resource);
// 4.之后就可以从工厂中正常获取对应的bean实例
        ClassA bean = beanFactory.getBean(ClassA.class);


//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        OrderService orderService = applicationContext.getBean(OrderService.class);
//
//        orderService.createOrder("20191130123");
//
//        applicationContext.close();
    }

    // https://www.gceasy.io/gc-index.jsp#banner
    public static void main(String[] args) throws InterruptedException {
//        List<String> res = new ArrayList<>();
        while (true) {
            List<String> list = new ArrayList<>();
            final int i1 = new Random().nextInt(1024 * 1024);
            for (int i = 0; i < i1; i++) {
                list.add(LocalDateTime.now().toString());
            }
//            res.add(list.get(new Random().nextInt(list.size())));
//            TimeUnit.MILLISECONDS.sleep(500);

//            if (list.size() == 999) {
//                break;
//            }
        }
//        System.out.println(res.size());

    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        long cur = Integer.MIN_VALUE - 1L;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.val <= cur) {
                    return false;
                } else {
                    cur = root.val;
                }
                root = root.right;
            }
        }
        return true;
    }


    public static int findDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = ((end - start) >> 1) + start;
            int count = count(nums, start, mid);
            if (start == end) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }

            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    private static int count(int[] nums, int start, int end) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
