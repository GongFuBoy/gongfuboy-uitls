package com.github.gongfuboy.utils.wechat.recharge;

import com.github.gongfuboy.utils.ModelToMapUtils;
import com.github.gongfuboy.utils.wechat.Constants;
import com.github.gongfuboy.utils.wechat.recharge.bean.WechatRechargeRequestBean;
import com.github.gongfuboy.utils.wechat.recharge.bean.WechatRechargeResponseBean;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信充值类
 *
 * @author GongFuBoy
 * @date 2017/12/19
 * @time 10:53
 */
public class WechatRechargeUtils {

    private final Log logger = LogFactory.getLog(WechatRechargeUtils.class);

    // 微信支付工具类
    private WXPay wxPay;

    private static WechatRechargeUtils wechatRechargeUtils;

    private WechatRechargeUtils() {

    }

    /**
     * 初始化微信充值类
     *
     * @param config
     * @return
     */
    public static WechatRechargeUtils instance(final WXPayConfig config) {
        if (wechatRechargeUtils == null) {
            synchronized (WechatRechargeUtils.class) {
                if (wechatRechargeUtils == null) {
                    wechatRechargeUtils = new WechatRechargeUtils();
                    wechatRechargeUtils.wxPay = new WXPay(config);
                }
            }
        }
        return wechatRechargeUtils;
    }

    /**
     * 微信支付方法
     *
     * @param wechatRechargeRequestBean 支付金额，订单编号类
     * @param wechatRechargeConfig      微信支付配置类
     * @return
     */
    public WechatRechargeResponseBean recharge(WechatRechargeRequestBean wechatRechargeRequestBean, WechatRechargeConfig wechatRechargeConfig) {
        WechatRechargeResponseBean result;
        Map<String, String> resultMap;
        Map<String, String> params = wechatRechargeConfig.getParams();
        Map<String, String> requestMap = new HashMap<String, String>(params);
        requestMap.put(Constants.OUT_TRADE_NO, wechatRechargeRequestBean.getOut_trade_no());
        requestMap.put(Constants.TOTAL_FEE, wechatRechargeRequestBean.getTotal_fee().toString());
        try {
            resultMap = wxPay.unifiedOrder(requestMap);
            boolean responseSignatureValid = wxPay.isResponseSignatureValid(requestMap);
            if (!responseSignatureValid) {
                logger.error("微信支付签名错误");
                throw new RuntimeException("微信支付-统一下单返回值签名错误");
            }
            result = ModelToMapUtils.mapToModel(WechatRechargeResponseBean.class, resultMap, null);
        } catch (Exception e) {
            logger.error("微信支付出现未知异常");
            throw new RuntimeException(e);
        }
        return result;
    }

}
