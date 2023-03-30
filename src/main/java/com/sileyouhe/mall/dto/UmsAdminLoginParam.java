package com.sileyouhe.mall.dto;

import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotEmpty;

public class UmsAdminLoginParam {

    @ApiModelProperty(value = "username",required = true)
    @NotEmpty(message = "username cannot be empty")
    private String username;

    @ApiModelProperty(value = "password", required = true)
    @NotEmpty(message = "password cannot be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
