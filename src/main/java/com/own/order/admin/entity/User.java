package com.own.order.admin.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "null")
@Getter
@Setter
public class User {
    @ApiModelProperty(value = "用户Id", required = false)
    private String userId;

    @ApiModelProperty(value = "用户姓名", required = false)
    private String name;

    @ApiModelProperty(value = "用户编码", required = false)
    private String code;

    @ApiModelProperty(value = "用户名", required = false)
    private String loginName;

    @ApiModelProperty(value = "密码", required = false)
    private String pwd;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", loginName=").append(loginName);
        sb.append(", pwd=").append(pwd);
        sb.append("]");
        return sb.toString();
    }
}