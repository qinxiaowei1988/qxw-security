package com.qxw.security.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MockQueue {
    private String placeOrder;//下单
    private String completeOrder;//订单完成消息

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {
       new Thread(()->{
           log.info("接收到下单请求="+placeOrder);
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           this.completeOrder=placeOrder;
           //this.placeOrder = placeOrder;
           log.info("处理完成订单"+placeOrder);
       }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
