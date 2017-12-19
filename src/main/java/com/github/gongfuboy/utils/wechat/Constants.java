package com.github.gongfuboy.utils.wechat;

/**
 * 微信工具，全文需要共享的固定值
 *
 * @author GongFuBoy
 * @date 2017/12/13
 * @time 18:02
 */
public class Constants {

    // 微信openid
    public static final String OPEN_ID = "openid";
    // 重定向路径，参数值
    public static final String GOTO = "goto";
    // 全文编码格式
    public static final String ENCODING = "UTF-8";
    // http连接符
    public static final String HTTP_CONNECT = "://";
    // 端口连接符
    public static final String PORT_CONNECT = ":";
    // code表示
    public static final String CODE = "code";
    // 微信支付订单key
    public static final String OUT_TRADE_NO = "out_trade_no";
    // 微信支付金额key
    public static final String TOTAL_FEE = "total_fee";
    // 微信支付请求标题
    public static final String BODY = "body";
    // 微信回调ip（这是一个公网的ip地址）
    public static final String SPBILL_CREATE_IP = "spbill_create_ip";
    // 微信支付交易类型，目前只支持公众号支付
    public static final String TRADE_TYPE = "trade_type";
    // 微信回调地址
    public static final String NOTIFY_URL = "notify_url";

}
