package com.qxw.security.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryCondition  {
    @ApiModelProperty(value = "测试key")
    private String testKey;
    @ApiModelProperty("用户名")
    private String userName;

}
