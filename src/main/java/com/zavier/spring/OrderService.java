package com.zavier.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
public class OrderService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserService userService;

    public OrderService(ApplicationEventPublisher applicationEventPublisher, UserService userService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userService = userService;
    }

    public void createOrder(String orderSn) throws ExecutionException, InterruptedException {
        //todo 创建订单的逻辑

        // 发送创建订单成功的消息
        log.info("createOrder threadId:{}", Thread.currentThread().getId());
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(this, orderSn));

        Future<String> stringFuture = userService.printUser();
        System.out.println(stringFuture.get());
    }
}
