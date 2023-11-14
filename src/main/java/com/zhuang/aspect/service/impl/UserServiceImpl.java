package com.zhuang.aspect.service.impl;

import com.zhuang.aspect.annotation.LogAnnotation;
import com.zhuang.aspect.dto.UserDto;
import com.zhuang.aspect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @LogAnnotation
    @Override
    public void list(UserDto dto) {
        int a=1/0;
        log.info(dto.toString());
    }

}
