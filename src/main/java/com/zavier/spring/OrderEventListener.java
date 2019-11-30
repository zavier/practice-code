package com.zavier.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventListener {

    @Async
    @EventListener
    public void orderCreatedListener(OrderCreatedEvent orderCreatedEvent) {
        log.info("orderCreatedListener threadId:{}", Thread.currentThread().getId());
        log.info("listen orderSn:" + orderCreatedEvent.getOrderSn() + " created");
        //todo 其他订单后处理，如通知其他系统等
//        throw new RuntimeException("YY");
    }
}
