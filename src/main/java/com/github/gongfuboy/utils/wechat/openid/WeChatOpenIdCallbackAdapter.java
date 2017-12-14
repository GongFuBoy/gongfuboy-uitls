package com.github.gongfuboy.utils.wechat.openid;

import com.github.gongfuboy.utils.wechat.Constants;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 微信获取openid适配器
 *
 * @author GongFuBoy
 * @date 2017/12/13
 * @time 18:57
 */
public class WeChatOpenIdCallbackAdapter {

    private final Log logger = LogFactory.getLog(WeChatOpenIdCallbackAdapter.class);

    // 创建http代理对象
    private final HttpClient httpClient = HttpClientBuilder.create().build();

    // 商户id
    private String appid;

    // 秘钥
    private String key;

    // 获取openid地址
    private String queryOpenidURI = "https://api.weixin.qq.com/sns/oauth2/access_token" + "?appid={appid}"
            + "&secret={secret}" + "&code={code}" + "&grant_type=authorization_code";

    private WeChatOpenIdCallbackAdapter() {

    }

    private static WeChatOpenIdCallbackAdapter weChatOpenIdCallbackAdapter;

    /**
     * 实例化WeChatOpenIdCallbackAdapter
     * @param appid
     * @param key
     * @return
     */
    public static WeChatOpenIdCallbackAdapter instance(String appid, String key) {
        if (weChatOpenIdCallbackAdapter == null) {
            synchronized (WeChatOpenIdCallbackAdapter.class) {
                if (weChatOpenIdCallbackAdapter == null) {
                    weChatOpenIdCallbackAdapter = new WeChatOpenIdCallbackAdapter();
                    weChatOpenIdCallbackAdapter.appid = appid;
                    weChatOpenIdCallbackAdapter.key = key;
                }
            }
        }
        return weChatOpenIdCallbackAdapter;
    }

    /**
     * 微信回调路径简单实现方法
     * @param request
     * @param response
     */
    public void callback(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String code = request.getParameter(Constants.CODE);
        String getOpenIdUrl = queryOpenidURI.replace("{appid}", appid).replace("{secret}", key)
                .replace("{code}", code);
        logger.debug("当前获取的code值：" + code);
        try {
            String result = queryOpenid(getOpenIdUrl);
            Gson gson = new Gson();
            Map fromJson = gson.fromJson(result, Map.class);
            Object openid = fromJson.get("openid");
            // 获取的openid放入session中
            session.setAttribute(Constants.OPEN_ID, openid);
            // 获取之前重定向地址
            String redirectURL = request.getParameter(Constants.GOTO);
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            logger.error("获取openid失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param getOpenIdUrl 获取openid
     * 微信获取openid
     * @return
     */
    public String queryOpenid(String getOpenIdUrl) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        HttpGet httpGet = new HttpGet(getOpenIdUrl);
        HttpResponse execute = httpClient.execute(httpGet);
        HttpEntity entity = execute.getEntity();
        InputStream content = entity.getContent();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, Constants.ENCODING));
        String line = bufferedReader.readLine();
        while (StringUtils.isNotBlank(line)) {
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }
        return bufferedReader.toString();
    }

}
