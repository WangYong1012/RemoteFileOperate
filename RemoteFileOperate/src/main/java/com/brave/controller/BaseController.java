package com.brave.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.Map;

public class BaseController
{
    //返回的成功或者失败的提示信息等参数
    private static final String FAIL_MSG = "获取错误";
    private static final String FAIL_CODE = "Fail";
    private static final String SUCCESS_MSG = "获取成功";
    private static final String SUCCESS_CODE = "Success";
    //日志文件记录
    private static final Logger log = Logger.getLogger(BaseController.class);

    /**
     * 2017年8月25日 09:55:21
     * 返回包含错误信息的失败提示字符串
     * @param paramMap
     * @param msg
     * @return
     */
    public String fail(Map<String, Object> paramMap, String msg)
    {
        paramMap.put("ResultMsg", FAIL_MSG);
        paramMap.put("ResultCode", FAIL_CODE);
        paramMap.put("ExceptionMsg", msg);
        return JSONObject.toJSONString(paramMap);
    }

    /**
     * 2017年8月25日 09:57:07
     * 返回包含成功信息的成功提示字符串
     * @param paramMap
     * @param data
     * @return
     */
    public String success(Map<String, Object> paramMap, Object data)
    {
        paramMap.put("ResultMsg", SUCCESS_MSG);
        paramMap.put("ResultCode", SUCCESS_CODE);
        paramMap.put("ResultData", data);
        return JSONObject.toJSONString(paramMap);
    }
}