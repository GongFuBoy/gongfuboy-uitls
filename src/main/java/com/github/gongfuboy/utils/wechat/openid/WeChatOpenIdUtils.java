package com.github.gongfuboy.utils.wechat.openid;

import com.github.gongfuboy.utils.wechat.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 微信获取openid工具
 *
 * @author GongFuBoy
 * @date 2017/12/13
 * @time 17:01
 */
public class WeChatOpenIdUtils {

    private final Log logger = LogFactory.getLog(WeChatOpenIdUtils.class);

    // app唯一表示
    private String appid;

    // 回调路径，必须是一个完整路径，例如：http://www.xxx.com/xxx/xxx
    // 这个回调路径，必须可以接受到微信服务器的回调，也就是说你在微信上面配置过
    // 关于回调controller的具体实现，可以直接使用WeChatOpenIdCallbackAdapter来实现，使用适配器模式来实现
    private String callbackUrl;

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
     * @param wechatVisitUrl
     * @return
     */
    public static WeChatOpenIdUtils instance(String appid, String callbackUrl, String wechatVisitUrl) {
        if (weChatOpenIdUtils == null) {
            synchronized (WeChatOpenIdUtils.class) {
                if (weChatOpenIdUtils == null) {
                    weChatOpenIdUtils = instance(appid, callbackUrl);
                    weChatOpenIdUtils.wechatVisitUrl = wechatVisitUrl;
                }
            }
        }
        return weChatOpenIdUtils;
    }

    /**
     * 获取openid，如果session中存在openid，那么直接返回，不存在则重定向，并返回null
     * @param request
     */
    public Object acquireOpenid(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object openid = session.getAttribute(Constants.OPEN_ID);
        if (openid != null) {
            return openid;
        }
        // 获取之前需要访问的路径
        String queryString = request.getQueryString();
        // 获取之前需要访问的URL
        String queryURL = request.getRequestURI();
        // 拼接完整访问路径
        if (StringUtils.isNotBlank(queryURL)) {
            queryURL = queryURL + "?" + queryString;
        }
        String absolutelyURL = request.getScheme() + Constants.HTTP_CONNECT + request.getServerName() + Constants.PORT_CONNECT + request.getServerPort();
        callbackUrl = callbackUrl + "?" + Constants.GOTO + "=" + absolutelyURL;
        // 微信服务器需要访问路径
        logger.debug("需要重定向的路径（这个路径是自己服务器的路径）：" + callbackUrl);
        String redirectUrl = wechatVisitUrl.replace("{callbackUrl}", callbackUrl).replace("{appid}", appid);
        logger.debug("处理之后的重定向路径（这个路径是微信服务器的路径）");
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            throw new RuntimeException("重定向失败");
        }
        return null;
    }

}
