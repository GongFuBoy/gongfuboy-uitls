package com.github.gongfuboy.utils.wechat.openid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信获取openid适配器
 *
 * @author GongFuBoy
 * @date 2017/12/13
 * @time 18:57
 */
public class WeChatOpenIdCallbackAdapter {

    private final Log logger = LogFactory.getLog(WeChatOpenIdCallbackAdapter.class);

    // 商户id
    private String appid;

    // 秘钥
    private String key;

    public void callback(HttpServletRequest request, HttpServletResponse response) {

    }
}
