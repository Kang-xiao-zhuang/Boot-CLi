package com.zhuang.aspect.controller;

import com.zhuang.aspect.annotation.LogAnnotation;
import com.zhuang.aspect.dto.UserDto;
import com.zhuang.aspect.enums.ResultEnum;
import com.zhuang.aspect.service.UserService;
import com.zhuang.aspect.util.ResultVOUtil;
import com.zhuang.aspect.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("get")
    public ResultVO getUser(@RequestBody UserDto userDto) {
        try {
            if (!ObjectUtils.isEmpty(userDto)) {
                userService.list(userDto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultVOUtil.error(ResultEnum.ERROR);
        }
        return ResultVOUtil.success(ResultEnum.SUCCESS);
    }

}
