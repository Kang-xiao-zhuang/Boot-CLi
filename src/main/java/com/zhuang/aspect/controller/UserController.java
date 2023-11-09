package com.zhuang.aspect.controller;

import com.zhuang.aspect.annotation.LogAnnotation;
import com.zhuang.aspect.dto.UserDto;
import com.zhuang.aspect.entity.User;
import com.zhuang.aspect.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @LogAnnotation
    @GetMapping("test")
    public ResultVO getUser(@RequestBody UserDto userDto){


        return null;
    }

}
