package com.qxw.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.qxw.security.entity.User;
import com.qxw.security.entity.dto.UserQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Api("用户接口")
@RestController
@RequestMapping("user")
public class UserController {

    @ApiOperation(value="用户接口")
    @GetMapping(value = "findAll")
    @JsonView(User.UserSimpleView.class)
    public Collection<User> findAll(UserQueryCondition user){
        System.out.println("dto:"+user);
        Date date =new Date();
        List<User> users = new ArrayList<User>();
        users.add(new User(1,"张三",20,"123",date));
        users.add(new User(2,"李四",30,"123",date));
        users.add(new User(3,"王五",30,"123",date));
        users.add(new User(4,"顺利",35,"123",date));
        return users;
    }

    @GetMapping("/info/{userId:\\d+}")
    public User getInfo(@PathVariable Integer userId){
        User user = new User();
        user.setUserAge(20);
        user.setUserId(userId);
        user.setUserName("张三");
        user.setPassword("123456");
        return user;
    }
    @PutMapping("/{userId:\\d+}")
    public  User update(@ApiParam("用户ID") @PathVariable Integer userId){

        return null;
    }

    @PostMapping("/save")
    public String  save(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){//判读是否参数是否有错误
            errors.getAllErrors().stream().forEach(error->{
                FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField() +" | "+ error.getDefaultMessage());
            });
        }
        return ("保存成功=>"+user);
    }
}
