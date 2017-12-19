package com.github.gongfuboy.utils.wechat.recharge;

import com.github.gongfuboy.utils.BeanUtils;
import com.github.gongfuboy.utils.enums.TransformEnum;
import com.github.gongfuboy.utils.wechat.common.Callback;
import com.github.gongfuboy.utils.wechat.recharge.bean.WechatCallbackAdapterResponseBean;
import com.github.gongfuboy.utils.wechat.recharge.enums.WechatResponseEnum;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 微信支付回调适配器类
 *
 * @author GongFuBoy
 * @date 2017/12/19
 * @time 14:59
 */
public class WechatRechargeCallbackAdapter implements Callback {

    private String key;

    private static WechatRechargeCallbackAdapter ourInstance = new WechatRechargeCallbackAdapter();

    public static WechatRechargeCallbackAdapter getInstance(String key) {
        ourInstance.key = key;
        return ourInstance;
    }

    private WechatRechargeCallbackAdapter() {
    }

    /**
     * 微信支付回调适配方法
     *
     * @param request
     * @param response
     */
    @Override
    public Map<String, String> callback(HttpServletRequest request, HttpServletResponse response) {
        WechatCallbackAdapterResponseBean result = new WechatCallbackAdapterResponseBean();
        String xmlResult = readFromRequst(request);
        try {
            Map<String, String> sourceMap = WXPayUtil.xmlToMap(xmlResult);
            if (!WXPayUtil.isSignatureValid(sourceMap, key)) {
                result.setReturn_code(WechatResponseEnum.FAIL.name());
                result.setReturn_msg("签名错误");
                String tempString = BeanUtils.transformBeanToXml(result, TransformEnum.HUNGARIAN_NOTATION);
                writeResultString(response, tempString);
                throw new RuntimeException("微信支付-回调签名错误");
            }
            return sourceMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将结果字符串写会微信服务器
     * @param response
     * @param sourceString
     */
    private void writeResultString(HttpServletResponse response, String sourceString) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(sourceString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    /**
     * 获取，解析微信请求参数
     * @param request
     * @return
     */
    private String readFromRequst(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            String line = reader.readLine();
            while (StringUtils.isNoneBlank(line)) {
                stringBuilder.append(line);
                line = reader.readLine();
                if (StringUtils.isNoneBlank(line)) {
                    stringBuilder.append(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return stringBuilder.toString();
    }

}
