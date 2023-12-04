package com.zhuang.aspect.service.impl;

import com.zhuang.aspect.annotation.LogAnnotation;
import com.zhuang.aspect.dto.UserDto;
import com.zhuang.aspect.entity.User;
import com.zhuang.aspect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @LogAnnotation
    @Override
    public void list(UserDto dto) {
        UserDto userDto = new UserDto();
        userDto.setName("zkkkkkkkk");
        log.info(dto.toString());
        log.info(userDto.toString());
    }

}
