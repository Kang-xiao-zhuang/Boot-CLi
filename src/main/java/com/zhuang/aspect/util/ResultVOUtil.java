package com.zhuang.aspect.util;

import com.zhuang.aspect.enums.ResultEnum;
import com.zhuang.aspect.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(Object object, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(ResultEnum.ERROR.getCode());
        return resultVO;
    }

    public static ResultVO error(String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(ResultEnum.ERROR.getCode());
        resultVO.setMsg(msg);
        return resultVO;
    }
}
