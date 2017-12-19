package com.github.gongfuboy.utils.wechat.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信客户端回调接口
 *
 * @author GongFuBoy
 * @date 2017/12/19
 * @time 14:55
 */
public interface Callback {

    /**
     * 微信回调接口
     * @param request
     * @param response
     */
    Map<String, String> callback(HttpServletRequest request, HttpServletResponse response);
}
