package com.qxw.security.listener;

import com.qxw.security.async.DeferredResultHolder;
import com.qxw.security.async.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
   private MockQueue mockQueue;
    @Autowired
   private DeferredResultHolder deferredResultHolder;
    @Value("${server.port}")
   private String port;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent appletEvent) {
        log.info("项目启动端口号："+port);
        new Thread(()->{
            while (true){
                if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){//处理订单
                    String orderNumber = mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果="+orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success number:"+orderNumber);
                    mockQueue.setCompleteOrder(null);
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
