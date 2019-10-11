package com.qxw.security.controller;

import com.qxw.security.async.DeferredResultHolder;
import com.qxw.security.async.MockQueue;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("async")
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/order")
    public String order() throws Exception {
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程结束");
        return "success";
    }

    @GetMapping("/order2")
    public Callable<String> order2(){
        String num =RandomStringUtils.random(7);
        logger.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(10000);
                logger.info("副线程结束");
                return "success";
            }
        };
        logger.info("主线程开始");
        return result ;
    }
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @GetMapping("/order3")
     public DeferredResult<String> order3() throws Exception {
           String orderNumber = RandomStringUtils.randomNumeric(8);//八位随机码
        System.out.println("===>"+orderNumber);
           mockQueue.setPlaceOrder(orderNumber);
           DeferredResult<String> result = new DeferredResult<>();
           deferredResultHolder.getMap().put(orderNumber,result);
           return result;
     }


}
