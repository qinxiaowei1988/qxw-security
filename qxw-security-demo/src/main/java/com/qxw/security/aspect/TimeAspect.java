package com.qxw.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    private Logger logger =LoggerFactory.getLogger(getClass());
    @Around("execution(* com.qxw.security.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp)throws  Throwable{
        logger.info("@Around环绕通知："+pjp.getSignature().toString());
        Object obj = null;
        try {
            obj = pjp.proceed(); //可以加参数
            logger.info(obj.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("@Around环绕通知执行结束");
        return obj;

    }
}
