package com.zhuang.aspect.dto;

import lombok.Data;

@Data
public class UserDto extends PageDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer status;
    private String mobile;
}
