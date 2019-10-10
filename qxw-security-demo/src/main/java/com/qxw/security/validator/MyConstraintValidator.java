package com.qxw.security.validator;

import com.qxw.security.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class MyConstraintValidator implements ConstraintValidator<MyConstaint,Object> {
    @Autowired
    private HelloServiceImpl helloService;
    @Override
    public void initialize(MyConstaint constraintAnnotation) {
        System.out.println("my validate init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("value"+value);
        helloService.hello("自定义执行类");
        return false;
    }
}
