package com.github.gongfuboy.utils.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 微信获取openid工具
 *
 * @author GongFuBoy
 * @date 2017/12/13
 * @time 17:01
 */
public class WeChatOpenIdUtils {

    // app唯一表示
    private String appid;

    // 回调路径，必须是一个完整路径，例如：http://www.xxx.com/xxx/xxx
    // 这个回调路径，必须可以接受到微信服务器的回调
    // 关于回调controller的具体实现，可以直接使用WeChatOpenIdCallbackAdapter来实现，使用适配器模式来实现
    private String callbackUrl;

    // openid在session存储的key值
    private String openidSessionKey = "openid";

    // 编码格式
    private String encoding = "UTF-8";

    // 微信获取openid访问路径，如果需要自定义的话，请一定要预留两个appid={appid}，callbackUrl={callbackUrl}出来
    private String wechatVisitUrl =  "https://open.weixin.qq.com/connect/oauth2/authorize" + "?appid={appid}"
            + "&callbackUrl={callbackUrl}" + "&response_type=code" + "&scope=snsapi_userinfo"
            + "&state=235#wechat_redirect";

    /**
     * 私有化构造方法
     */
    private WeChatOpenIdUtils() {

    }

    // 持有自己的对象
    private static WeChatOpenIdUtils weChatOpenIdUtils;

    /**
     * 提供单例实现方法
     * @param appid
     * @param callbackUrl
     * @return
     */
    public static WeChatOpenIdUtils instance(String appid, String callbackUrl) {
        if (weChatOpenIdUtils == null) {
            synchronized (WeChatOpenIdUtils.class) {
                if (weChatOpenIdUtils == null) {
                    weChatOpenIdUtils = new WeChatOpenIdUtils();
                    weChatOpenIdUtils.appid = appid;
                    weChatOpenIdUtils.callbackUrl = callbackUrl;
                }
            }
        }
        return weChatOpenIdUtils;
    }

    /**
     * 提供参数配置，单例方法
     * @param appid
     * @param callbackUrl
     * @param encoding
     * @param wechatVisitUrl
     * @return
     */
    public static WeChatOpenIdUtils instance(String appid, String callbackUrl, String encoding, String wechatVisitUrl, String openidSessionKey) {
        if (weChatOpenIdUtils == null) {
            synchronized (WeChatOpenIdUtils.class) {
                if (weChatOpenIdUtils == null) {
                    weChatOpenIdUtils = instance(appid, callbackUrl);
                    weChatOpenIdUtils.encoding = encoding;
                    weChatOpenIdUtils.wechatVisitUrl = wechatVisitUrl;
                    weChatOpenIdUtils.openidSessionKey = openidSessionKey;
                }
            }
        }
        return weChatOpenIdUtils;
    }

    /**
     * 获取openid，如果session中存在openid，那么直接返回，不存在则重定向
     * @param request
     */
    public Object acquireOpenid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object openid = session.getAttribute(openidSessionKey);
        if (openid != null) {
            return openid;
        }
        return null;
    }

}
