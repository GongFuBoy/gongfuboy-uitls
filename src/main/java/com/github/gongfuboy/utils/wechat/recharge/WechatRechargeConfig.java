package com.github.gongfuboy.utils.wechat.recharge;

import com.github.gongfuboy.utils.wechat.Constants;
import com.github.gongfuboy.utils.wechat.recharge.enums.CreatePatternEnum;
import com.github.gongfuboy.utils.wechat.recharge.enums.TradeTypeEnum;
import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信充值配置类
 *
 * @author GongFuBoy
 * @date 2017/12/19
 * @time 10:32
 */
public class WechatRechargeConfig {

    private static WechatRechargeConfig wechatRechargeConfig;

    // 参数配置map
    private Map<String, String> params;

    private WechatRechargeConfig() {

    }

    /**
     * 实例方法
     *
     * @param createPatternEnum 创建模式
     * @param body              支付标题
     * @param spbill_create_ip  回调ip
     * @param tradeTypeEnum     交易类型
     * @param notify_url        回调地址
     * @return
     */
    public static WechatRechargeConfig instance(CreatePatternEnum createPatternEnum, String body,
                                                String spbill_create_ip, TradeTypeEnum tradeTypeEnum, String notify_url) {
        // 默认采用单例模式创建
        if (CreatePatternEnum.SINGLETON_PATTERN.equals(createPatternEnum) || createPatternEnum == null) {
            if (wechatRechargeConfig == null) {
                synchronized (WechatRechargeConfig.class) {
                    if (wechatRechargeConfig == null) {
                        wechatRechargeConfig = new WechatRechargeConfig();
                        wechatRechargeConfig.params = new HashMap<String, String>();
                        wechatRechargeConfig.params.put(Constants.BODY, body);
                        wechatRechargeConfig.params.put(Constants.SPBILL_CREATE_IP, spbill_create_ip);
                        wechatRechargeConfig.params.put(Constants.TRADE_TYPE, tradeTypeEnum.name());
                        wechatRechargeConfig.params.put(Constants.NOTIFY_URL, notify_url);
                    }
                }
            }
            return wechatRechargeConfig;
        } else {
            WechatRechargeConfig tempWechatRechargeConfig = new WechatRechargeConfig();
            tempWechatRechargeConfig.params = new HashMap<String, String>();
            tempWechatRechargeConfig.params.put(Constants.BODY, body);
            tempWechatRechargeConfig.params.put(Constants.SPBILL_CREATE_IP, spbill_create_ip);
            tempWechatRechargeConfig.params.put(Constants.TRADE_TYPE, tradeTypeEnum.name());
            tempWechatRechargeConfig.params.put(Constants.NOTIFY_URL, notify_url);
            return tempWechatRechargeConfig;
        }
    }

    /**
     * 获取map参数
     *
     * @return
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * 这是一个WXPayConfig简单内部实现类
     */
    public static class InnerWXPayConfigImpl implements WXPayConfig {

        private byte[] certData;
        private static InnerWXPayConfigImpl INSTANCE;

        //商户证书绝对路劲
        private static String certPath;

        //appID
        private static String appId;

        //Mch ID
        private static String mchID;

        //秘钥
        private static String key;

        private InnerWXPayConfigImpl() throws Exception {
            File file = new File(certPath);
            InputStream certStream = new FileInputStream(file);
            this.certData = new byte[(int) file.length()];
            certStream.read(this.certData);
            certStream.close();
        }

        /**
         * 获取内部微信配置类实例
         *
         * @param certPath 微信支付证书路径
         * @param appId    appid
         * @param mchID    商户id
         * @param key      微信支付秘钥，这里注意一点，这不是微信商户的key，是微信支付的key（微信支付key需要申请微信支付后，自定义设置的）
         * @return
         */
        public static InnerWXPayConfigImpl getInstance(String certPath, String appId, String mchID, String key) {
            if (INSTANCE == null) {
                synchronized (InnerWXPayConfigImpl.class) {
                    if (INSTANCE == null) {
                        InnerWXPayConfigImpl.certPath = certPath;
                        InnerWXPayConfigImpl.appId = appId;
                        InnerWXPayConfigImpl.mchID = mchID;
                        InnerWXPayConfigImpl.key = key;
                        try {
                            INSTANCE = new InnerWXPayConfigImpl();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            return INSTANCE;
        }

        public String getAppID() {
            return appId;
        }

        public String getMchID() {
            return mchID;
        }

        public String getKey() {
            return key;
        }

        public InputStream getCertStream() {
            ByteArrayInputStream certBis;
            certBis = new ByteArrayInputStream(this.certData);
            return certBis;
        }

        public int getHttpConnectTimeoutMs() {
            return 8000;
        }

        public int getHttpReadTimeoutMs() {
            return 10000;
        }
    }


}
