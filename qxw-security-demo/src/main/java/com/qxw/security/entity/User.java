package com.qxw.security.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.qxw.security.validator.MyConstaint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonView(UserSimpleView.class)
    private Integer userId;
    @MyConstaint(message = "这个是测试注解")
    @JsonView(UserSimpleView.class)
    private String userName;
    @JsonView(UserSimpleView.class)
    private Integer userAge;
    @NotBlank(message = "不为空")
    @JsonView(UserDetailView.class)
    private String password;
    @Past
    @JsonView(UserSimpleView.class)
    private Date birthDay;

    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

}
