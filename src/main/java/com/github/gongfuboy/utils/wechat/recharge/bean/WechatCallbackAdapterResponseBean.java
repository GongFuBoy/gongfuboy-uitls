package com.github.gongfuboy.utils.wechat.recharge.bean;

/**
 * 微信支付回调响应bean
 *
 * @author GongFuBoy
 * @date 2017/12/19
 * @time 16:29
 */
public class WechatCallbackAdapterResponseBean {

    // 响应编码
    private String return_code;

    // 响应信息
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
