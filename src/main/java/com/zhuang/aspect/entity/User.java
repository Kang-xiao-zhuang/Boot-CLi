package com.zhuang.aspect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer status;
    private String mobile;

}
