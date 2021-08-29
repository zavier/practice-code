package com.zavier.spring;

import com.zavier.annotation.Builder;
import org.springframework.context.ApplicationEvent;

@Builder
public class OrderCreatedEvent extends ApplicationEvent {
    private String orderSn;

    public OrderCreatedEvent(Object source) {
        super(source);
    }

    public OrderCreatedEvent(Object source, String orderSn) {
        super(source);
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return this.orderSn;
    }
}
