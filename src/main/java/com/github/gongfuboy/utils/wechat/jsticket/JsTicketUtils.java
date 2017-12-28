package com.github.gongfuboy.utils.wechat.jsticket;

import com.github.gongfuboy.utils.HttpUtils;
import com.github.gongfuboy.utils.wechat.jsticket.bean.AccessToken;
import com.github.gongfuboy.utils.wechat.jsticket.bean.JsTicket;
import com.github.gongfuboy.utils.wechat.jsticket.enums.JsApiTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取微信js调用权限工具
 *
 * @author GongFuBoy
 * @date 2017/12/28
 * @time 16:15
 */
public class JsTicketUtils {

    // js_ticket缓存池
    private final static ConcurrentHashMap<String, JsTicket> JS_TICKET_CACHE = new ConcurrentHashMap();
    // 获取js_ticket路径
    private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={access_token}&type={type}";
    // appid
    private String appId;

    // 微信公众号私钥
    private String key;

    // 私有化构造方法
    private JsTicketUtils() {

    }

    public JsTicketUtils(String appId, String key) {
        JsTicketUtils jsTicketUtils = new JsTicketUtils();
        jsTicketUtils.key = key;
        jsTicketUtils.appId = appId;
    }

    /**
     * 获取js_ticket方法
     * @return
     */
    public JsTicket getJsTicket() {
        JsTicket jsTicket = JS_TICKET_CACHE.get(this.appId);
        if (jsTicket != null && jsTicket.isAvailable()) {
            return jsTicket;
        }
        // 如果当前没有可用的js_ticket，那么首先获取token
        TokenUtils tokenUtils = new TokenUtils(appId, key);
        AccessToken accessToken = tokenUtils.queryAccessToken();
        if (accessToken != null && accessToken.isAvailable()) {
            String tokenString = accessToken.getAccessToken();
            String realJsTicketUrl = StringUtils.replace(StringUtils.replace(TICKET_URL, "access_token}", tokenString)
                    , "{type}", JsApiTypeEnum.jsapi.name());
            // 最大请求三次，成功一次就中断
            for (int i=0; i < 3; i++) {
                String result = HttpUtils.get(realJsTicketUrl);
                jsTicket = new JsTicket(result);
                if (jsTicket != null && jsTicket.isAvailable()) {
                    break;
                }
            }
        }
        JS_TICKET_CACHE.put(this.appId, jsTicket);
        return jsTicket;
    }

    /**
     * 获取jsTicket缓存池
     * @return
     */
    public static ConcurrentHashMap getJsTicketCache() {
        return JS_TICKET_CACHE;
    }
}
