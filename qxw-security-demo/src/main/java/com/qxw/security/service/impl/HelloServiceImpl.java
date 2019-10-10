package com.qxw.security.service.impl;

import com.qxw.security.service.HelleService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl  implements HelleService {
    @Override
    public void hello(String name) {
        System.out.println("执行了服务"+name);
    }
}
