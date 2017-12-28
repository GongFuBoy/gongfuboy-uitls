package com.github.gongfuboy.utils.wechat.jsticket;

import com.github.gongfuboy.utils.HttpUtils;
import com.github.gongfuboy.utils.wechat.jsticket.bean.AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取微信token工具
 *
 * @author GongFuBoy
 * @date 2017/12/28
 * @time 16:29
 */
public class TokenUtils {

    private static final Log logger = LogFactory.getLog(TokenUtils.class);

    // token保存池
    private static final ConcurrentHashMap<String, AccessToken> TOKEN_CACHE = new ConcurrentHashMap();

    // 微信token获取地址
    private final String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential?appid={appid}&secret={secret}";

    // appid
    private String appId;

    // 微信秘钥
    private String key;

    private TokenUtils() {

    }

    public TokenUtils(String appId, String key) {
        TokenUtils tokenUtils = new TokenUtils();
        tokenUtils.key = key;
        tokenUtils.appId = appId;
    }

    /**
     * 获取AccessToken
     * @return
     */
    public AccessToken queryAccessToken() {
        // 首先从缓存中获取当前accessToken
        AccessToken accessToken = TOKEN_CACHE.get(this.appId);
        if (accessToken != null && accessToken.isAvailable()) {
            return accessToken;
        }
        String realTokenUrl = StringUtils.replace(StringUtils.replace(tokenUrl, "{appid}", this.appId), "{secret}", this.key);
        String result = null;
        for (int i=0; i < 3; i++) {
            try {
                result = HttpUtils.get(realTokenUrl);
                accessToken = new AccessToken(result);
                if (accessToken.isAvailable()) {
                    break;
                }
            } catch (Exception e) {
                logger.warn("微信获取token失败", e);
            }
        }
        // 将获取的accessToken放入缓存中，如果accessToken是可用的直接放入，如果三次请求都没有获取正确的accessToken，依然放入
        TOKEN_CACHE.put(this.appId, accessToken);
        return accessToken;
    }

    /**
     * 提供token池公共方法访问
     * @return
     */
    public static ConcurrentHashMap getTokenCache() {
        return TOKEN_CACHE;
    }
}
